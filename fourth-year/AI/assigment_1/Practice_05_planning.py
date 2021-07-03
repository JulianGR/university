
# coding: utf-8

# In order to implement a planning problem we shall make use of the classes provided by the `planning_problem_pddl` (**Note**: it's important to take into account that this module considers all symbols for objects are strings).

# In[ ]:


import planning_problem_pddl as pddl


# Let us begin with the examples from the unit slides: flat tyre example and the blocks world.

# # Flat world problem

# Flat tyre problem: determine the steps that should be made in order to replace a flat tyre by the spare tyre which is in the trunk. We should also end up by puting the flat tyre on the trunk so that we can continue driving.

# Let us first declare the predicates that will be used.

# In[ ]:


at = pddl.Predicate({'flat-tyre','spare-tyre'},{'axle','trunk','ground'})


# A state is an instance of the class `State`, created from a sequence of instances of previously created predicates.

# In[ ]:


initial_state_tyre = pddl.State(at('flat-tyre','axle'),at('spare-tyre','trunk'))
print(initial_state_tyre)


# Actions are implemented as instances of the class `PlanningAction`. Its arguments are the following:
# * `name`: a string describing the action. This argument is mandatory.
# * `preconditionsP`: a list of instances of predicates (positive preconditions). This argument is optional.
# * `preconditionsN`: a list of instances of predicates (negative preconditions). This argument is optional.
# * `effectsP`: a list of instances of predicates (positive effects). This argument is optional.
# * `effectsN`: a list of instances of predicates (negative effects). This argument is optional.
# * `cost`: a positive integer (our implementation assumes that the cost of applying an action is always the same, irrespectively of the state). This argument is optional (defaults to 1).
# 
# If we have only one precondition or effect it is not necessary to write it as a list.

# In[ ]:


# Take the spare tyre out of the trunk
takeOut = pddl.PlanningAction(
    name = 'take_out_spare',
    preconditionsP = at('spare-tyre','trunk'),
    effectsP = at('spare-tyre','ground'),
    effectsN = at('spare-tyre','trunk'))

# Remove flat tyre from axle
remove = pddl.PlanningAction(
    name = 'remove_flat',
    preconditionsP = [at('flat-tyre','axle')],
    effectsP = [at('flat-tyre','ground')],
    effectsN = [at('flat-tyre','axle')])

# Install the spare tyre on the axle
install = pddl.PlanningAction(
    name = 'install_spare',
    preconditionsP = at('spare-tyre','ground'),
    preconditionsN = at('flat-tyre','axle'),
    effectsP = at('spare-tyre','axle'),
    effectsN = at('spare-tyre','ground'))

# Pick up the flat tyre and put it in the trunk
pickUp = pddl.PlanningAction(
    name = 'pickUp_flat',
    preconditionsP = [at('flat-tyre','ground')],
    preconditionsN = [at('spare-tyre','trunk')],
    effectsP = [at('flat-tyre','trunk')],
    effectsN = [at('flat-tyre','ground')])


# After creating the actions, let us use `print` to see their structure.

# In[ ]:


print(remove)


# In[ ]:


print(pickUp)


# Finally, our planning problems will be instances of the class `PlanningProblem` built using the following arguments:
# * `operators`: list of actions of the problem.
# * `initial_state`: initial state of the problem.
# * `goalsP`: a list of instances of predicates that form positive goals.
# * `goalsN`: a list of instances of predicates that form negative goals.
# 
# In case we have only one operator, only one positive goal or only one negative goal, it is not necessary to write it as a list.

# In[ ]:


flat_tyre_problem = pddl.PlanningProblem(
    operators=[remove, pickUp, takeOut, install],
    initial_state=pddl.State(at('flat-tyre','axle'),
                                 at('spare-tyre','trunk')),
    goalsP=[at('flat-tyre','trunk'), 
                at('spare-tyre','axle')])


# Once implemented the planning problem, if we want to find a solution (plan) it suffices to apply some search algorithm.

# In[ ]:


import state_space_search as sssearch


# In[ ]:


dfs = sssearch.DepthFirstSearch()

dfs.search(flat_tyre_problem)


# In[ ]:


bfs = sssearch.BreadthFirstSearch()

bfs.search(flat_tyre_problem)


# # Problem of the blocks world

# Let us first declare the predicates that will be used to represent the problem, indicating a set of ranges for each argument. For predicates with no arguments, we indicate the empty set.

# In[ ]:


blocks = {'A','B','C'}
clear = pddl.Predicate(blocks)
freearm = pddl.Predicate({})
onthetable = pddl.Predicate(blocks)
on = pddl.Predicate(blocks,blocks)
hold = pddl.Predicate(blocks)


# Let us define an initial state for the blocks problem where block $A$ is on the table with nothing on top of it; block $B$ is on the table and has $C$ on top of it, and nothing else on top of $C$; and the robotic arm is free.

# In[ ]:


initial_state_blocks = pddl.State(
    onthetable('A'),clear('A'),
    onthetable('B'),on('C','B'),clear('C'),
    freearm())


# We can set different costs even for actions obtenained from the same scheme. In order to do so, we may create an instance of the class `CostScheme` providing a function that sets the desired cost with respect to some parameters. For example, assume different costs for each blocks (e.g. having different weight).

# In[ ]:


cost_block = pddl.CostScheme(lambda b: {'A': 1, 'B': 2, 'C': 3}[b])


# Action schemes are implemented as instances of the class `PlanningScheme`. Arguments that can be provided are the following:
# * `name`: a string of the form $act(z_1, \dotsc, z_k)$, where if $z_i$ represents a variable, it should be written in curly brackets. This argument is mandatory.
# * `preconditionsP`: a list of instances of predicates forming positive preconditions. This argument is optional.
# * `preconditionsN`: a list of instances of predicates forming negative preconditions. This argument is optional.
# * `effectsP`: a list of instances of predicates forming positive effects. This argument is optional.
# * `effectsN`: a list of instances of predicates forming negative effects. This argument is optional.
# * `cost`: an instance of the class `CostScheme` that sets the cost of an action with respect to the values of variables $z_i$. This argument is optional (default cost is 1).
# * `domain`: a set of tuples of the same length as the number of variables. Indicates the set of situations for which it makes sense to instantiate the action scheme.
# * `variables`: a dictionary associating to each variable name $z_i$ the set of values that it may take.
# 
# At least one of the arguments `domain` or `variables` must appear. If both are included, only `domain` will be considered.
# 
# The instances of predicates within `preconditionsP`, `preconditionsN`, `effectsP` and `effectsN`, may refer to variables $z_i$, which should be written between curly brackets. In case we have only one positive (or negative) precondition or only one positive or negative effect, it is not necessary to write them as a list.

# In[ ]:


# Pile a block on top of another
pile = pddl.PlanningScheme('pile({x},{y})',
    preconditionsP = [clear('{y}'),hold('{x}')],
    effectsN = [clear('{y}'),hold('{x}')],
    effectsP = [clear('{x}'),freearm(),on('{x}','{y}')],
    cost = cost_block('{x}'),
    domain = {('A','B'),('A','C'),('B','A'),('B','C'),('C','A'),('C','B')},
    variables = {'x':blocks,'y':blocks})

# Lift a block which was on top of another
unpile = pddl.PlanningScheme('unpile({x},{y})',
    preconditionsP = [on('{x}','{y}'),clear('{x}'),freearm()],
    effectsN = [on('{x}','{y}'),clear('{x}'),freearm()],
    effectsP = [hold('{x}'),clear('{y}')],
    cost = cost_block('{x}'),
    domain = {('A','B'),('A','C'),('B','A'),('B','C'),('C','A'),('C','B')})

# Grab a block from the table
grab = pddl.PlanningScheme('grab({x})',
    preconditionsP = [clear('{x}'),onthetable('{x}'),freearm()],
    effectsN = [clear('{x}'),onthetable('{x}'),freearm()],
    effectsP = [hold('{x}')],
    cost = cost_block('{x}'),
    domain = blocks)

# Release a block on the table
release = pddl.PlanningScheme('release({x})',
    preconditionsP = [hold('{x}')],
    effectsN = [hold('{x}')],
    effectsP = [clear('{x}'),onthetable('{x}'),freearm()],
    cost = cost_block('{x}'),
    variables = {'x':blocks})


# String representation of an action scheme, showing the actions obtained from it.

# In[ ]:


print(grab)


# In[ ]:


print(pile)


# Finally, in order to represent the planning problem, we provide the list of action schemes to the class `PlanningProblem` (in general, we can provide both actions and operators, or even both). 

# In[ ]:


problem_blocks_world = pddl.PlanningProblem(
    operators = [pile,unpile,grab,release],
    initial_state = initial_state_blocks,
    goalsP = [onthetable('C'),on('B','C'),on('A','B')])


# Once implemented the planning problem, if we want to find a solution (plan) it suffices to apply some search algorithm.

# In[ ]:


dfs.search(problem_blocks_world)


# __Exercise 1__: implement the planning problem described in Exercise 2 in the collection of exercises, and find a solution to it.

# __Exercise 2__: implement the planning problem described in Exercise 11 in the collection of exercises, and find a solution to it.
