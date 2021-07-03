class Action:
    def __init__(self, name='', applicability=None, application=None, cost=None):
        self.name = name
        self.applicability = applicability
        self.application = application
        self.cost = cost

    def is_applicable(self, state):
        if self.applicability is None:
            raise NotImplementedError('Applicability of the action not implemented')
        else:
            return self.applicability(state)

    def execute(self, state):
        if self.execute is None:
            raise NotImplementedError('Execution of the action not implemented')
        else:
            return self.application(state)

    def cost_of_applying(self, state):
        if self.cost is None:
            return 1
        else:
            return self.cost(state)

    def __str__(self):
        return 'Action: {}'.format(self.name)


class StatesSpaceProblem:
    def __init__(self, actions, initial_state=None, final_states=None):
        if not isinstance(actions, list):
            raise TypeError('Expected a list of actions')
        self.actions = actions
        self.initial_state = initial_state
        self.final_states = final_states

    def is_final_state(self, state):
        return state in self.final_states

    def applicable_actions(self, state):
        return (action
                for action in self.actions
                if action.is_applicable(state))
