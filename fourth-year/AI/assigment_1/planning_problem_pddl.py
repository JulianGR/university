import inspect
import re
import itertools
import copy
import state_space_problem as stsp

class Predicate:
    def __init__(self, *domains):
        self.domains = [] if domains == ({},) else list(domains)
        self.name = None
        self.signo = True
        
    def __str__(self):
        if self.name == None:
            ans = []        
            frame = inspect.currentframe().f_back
            tmp = dict(frame.f_globals.items())
            for k, var in tmp.items():
                if isinstance(var, self.__class__):
                    if hash(self) == hash(var):
                        ans.append(k)
            tmp = dict(frame.f_locals.items())
            for k, var in tmp.items():
                if isinstance(var, self.__class__):
                    if hash(self) == hash(var):
                        ans.append(k)
            self.name = ans[0]
        return self.name

    def __call__(self, *arguments):
        if self.name == None:
            ans = []        
            frame = inspect.currentframe().f_back
            tmp = dict(frame.f_globals.items())
            for k, var in tmp.items():
                if isinstance(var, self.__class__):
                    if hash(self) == hash(var):
                        ans.append(k)
            tmp = dict(frame.f_locals.items())
            for k, var in tmp.items():
                if isinstance(var, self.__class__):
                    if hash(self) == hash(var):
                        ans.append(k)
            self.name = ans[0]
        dictionary = {}
        if (len(self.domains) == len(arguments) and
            all([arguments[i] in self.domains[i] 
                 for i in range(len(arguments))
                 if not re.fullmatch('{[^}]+}',arguments[i])])):  
            dictionary[self.name] = {tuple(arguments)}
        else:
            raise ValueError('Wrong arguments')
        return dictionary

def group_dictionaries(dictionaries):
    if dictionaries is None:
        dictionaries = []
    if not isinstance(dictionaries,list):
        dictionaries = [dictionaries]
    dictionary_total = {}
    for dictionary in dictionaries:
        for key in dictionary.keys():
            if key in dictionary_total:
                dictionary_total[key].update(dictionary[key])
            else:
                dictionary_total[key] = dictionary[key]
    return dictionary_total

# A State is a set of positive and closed atoms, standing for facts which are True.
# Any instance of an atom which does not belong to a state is considered False.
# Example:
#    State({clear(B), clear(C), clear(D), freearm(),
#            on(B,A), onthetable(C), onthetable(D), onthetable(A)})
# Representation: Dictionary for each predicate symbol associates
# the set of possible tuples representing instances of this
# predicate which are True.
# Example:
#    {'clear' : {(B), (C), (D)},
#     'freearm' : {()},
#     'on': {(B,A)},
#     'onthetable': {(C), (D), (A)}}

class State:
    def __init__(self, *atoms):
        self.atoms = {}
        for atom in atoms:
            for key,value in atom.items():
                if key in self.atoms:
                    self.atoms[key] = self.atoms[key].union(value)
                else:
                    self.atoms[key] = value

    def __str__(self):
        return '\n'.join('{}({})'.format(key, ','.join('{}'.format(arg)
                                                       for arg in valor))
                         for key, values in self.atoms.items()
                         for valor in values)

    def __eq__(self, otro):
        return self.atoms == otro.atoms
 
    def satisfies_positive(self, condiciones):
        return all(key in self.atoms.keys() and
                   value in self.atoms[key] 
                   for key in condiciones.keys()
                   for value in condiciones[key])
        
    def satisfies_negative(self, condiciones):
        return all(key not in self.atoms.keys() or
                   value not in self.atoms[key] 
                   for key in condiciones.keys()
                   for value in condiciones[key])

#------------------------------------------------------------------------------

class PlanningAction(stsp.Action):
    def __init__(self, name,
                 preconditionsP=None, preconditionsN=None,
                 effectsP=None, effectsN=None, cost=1):
        self.name = name
        
        self.preconditionsP = group_dictionaries(preconditionsP)
        self.preconditionsN = group_dictionaries(preconditionsN)
        self.effectsP = group_dictionaries(effectsP)
        self.effectsN = group_dictionaries(effectsN)
        
        self.cost = cost

    def is_applicable(self, state):
        return (state.satisfies_positive(self.preconditionsP) and
                state.satisfies_negative(self.preconditionsN))

    def apply(self, state):
        new_state = copy.deepcopy(state)
        for key in self.effectsN.keys():
            for value in self.effectsN[key]: 
                if key in new_state.atoms.keys():
                    new_state.atoms[key].discard(value)
        for key in self.effectsP.keys():
            if key in new_state.atoms.keys():
                new_state.atoms[key].update(self.effectsP[key])
            else:
                new_state.atoms[key] = self.effectsP[key]
        return new_state

    def cost_of_applying(self, state):
        return self.cost

    def __str__(self):
        return ('\nAction: ' + self.name + 
                '\n  Preconditions:\n    ' +
                '\n    '.join(['-{}({})'.format(key, ','.join('{}'.format(arg)
                                                for arg in valor))
                              for key, values in self.preconditionsN.items()
                              for valor in values] +
                              ['{}({})'.format(key, ','.join('{}'.format(arg)
                                               for arg in valor))
                              for key, values in self.preconditionsP.items()
                              for valor in values]) +
                '\n  Effects:\n    ' +
                '\n    '.join(['-{}({})'.format(key, ','.join('{}'.format(arg)
                                                for arg in valor))
                              for key, values in self.effectsN.items()
                              for valor in values] +
                              ['{}({})'.format(key, ','.join('{}'.format(arg)
                                               for arg in valor))
                              for key, values in self.effectsP.items()
                              for valor in values]) +
                '\n  Cost: ' + str(self.cost))

#------------------------------------------------------------------------------

def instantiate(dictionary, assignation):
    instance = {}
    for key in dictionary:
        instance[key] = set()
        for value in dictionary[key]:
            instance[key].update({tuple(argument.format(**assignation)
                                    for argument in value)})
        if instance[key] == set():
            instance[key] = {()}
    return instance

class PlanningScheme:
    def __init__(self, name, 
                 preconditionsP = None, preconditionsN = None,
                 effectsP = None, effectsN = None,
                 cost = None, domain = None, variables = None):

        self.name = name
        self.names_variables = re.findall('{([^}]*)}',name)
        self.preconditionsP = group_dictionaries(preconditionsP)
        self.preconditionsN = group_dictionaries(preconditionsN)
        self.effectsP = group_dictionaries(effectsP)
        self.effectsN = group_dictionaries(effectsN)
        self.domain = domain
        self.variables = variables
        if cost is None or isinstance(cost, int):
            cost = CostScheme(cost)()
        self.cost_scheme = cost
    
    def obtain_action(self,assignation):
        name = self.name.format(**assignation)
        preconditionsP = instantiate(self.preconditionsP,assignation)
        preconditionsN = instantiate(self.preconditionsN,assignation)
        effectsP = instantiate(self.effectsP,assignation)
        effectsN = instantiate(self.effectsN,assignation)
        cost = self.cost_scheme.cost(assignation)
        return PlanningAction(
                name, preconditionsP, preconditionsN,
                effectsP, effectsN, cost)        
    
    def obtain_actions(self):
        if self.domain == None:
            values_variables = [self.variables[key] 
                                 for key in self.names_variables
                                 if key in self.variables.keys()]
            product_values = itertools.product(*values_variables)
            assignations = (dict(zip(self.names_variables, values))
                            for values in product_values)
        else:
            assignations = (dict(zip(self.names_variables, values))
                            for values in self.domain)
        return [self.obtain_action(assignation)
                for assignation in assignations]

    def __str__(self):
        actions = self.obtain_actions()
        return ('Operator: ' + self.name +
                '\nGENERATED ACTIONS:\n' +
                '\n'.join(str(action) for action in actions))

#------------------------------------------------------------------------------


class CostScheme:
    def __init__(self, cost=None):
        if cost is None:
            cost = 1
        if isinstance(cost, int):
            def cost_function(*arguments):
                return cost
            self.cost_function = cost_function
        else:
            self.cost_function = cost

    def cost(self, assignation):
        return self.cost_function(*(argument.format(**assignation)
                                    for argument in self.arguments))

    def __call__(self, *arguments):
        cost_scheme = copy.deepcopy(self)
        cost_scheme.arguments = arguments
        return cost_scheme

#------------------------------------------------------------------------------

class PlanningProblem(stsp.StatesSpaceProblem):
    def __init__(self, operators, initial_state, 
                 goalsP=None, goalsN=None):
        
        self.goalsP = group_dictionaries(goalsP)
        self.goalsN = group_dictionaries(goalsN)

        if not isinstance(operators, list):
            operators = [operators]
        actions = sum(([operator] if isinstance(operator, PlanningAction)
                       else operator.obtain_actions()
                       for operator in operators), [])
        
        super().__init__(actions, initial_state)

    def is_final_state(self, state):
        return (state.satisfies_positive(self.goalsP) and
                state.satisfies_negative(self.goalsN))
