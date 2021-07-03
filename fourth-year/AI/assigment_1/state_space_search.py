import collections
import heapq
import types


class NodeList(collections.deque):
    def add(self, node):
        self.append(node)

    def empty(self):
        self.clear()

    def __contains__(self, node):
        return any(x.state == node.state
                   for x in self)


class NodePile(NodeList):
    def extract(self):
        return self.pop()


class NodeQueue(NodeList):
    def extract(self):
        return self.popleft()


class NodeQueueWithPriority:
    def __init__(self):
        self.nodes = []
        self.node_generated = 0

    def add(self, node):
        heapq.heappush(self.nodes, (node.heuristic, self.node_generated, node))
        self.node_generated += 1

    def extract(self):
        return heapq.heappop(self.nodes)[2]

    def empty(self):
        self.__init__()

    def __iter__(self):
        return iter(self.nodes)

    def __contains__(self, node):
        return any(x[2].state == node.state and
                   x[2].heuristic <= node.heuristic
                   for x in self.nodes)


class SimpleNode:
    def __init__(self, state, parent=None, action=None):
        self.state = state
        self.parent = parent
        self.action = action

    def is_root(self):
        return self.parent is None

    def successor(self, action):
        Node = self.__class__
        return Node(action.apply(self.state), self, action)

    def solution(self):
        if self.is_root():
            actions = []
        else:
            actions = self.parent.solution()
            actions.append(self.action.name)
        return actions

    def __str__(self):
        return 'State: {}'.format(self.state)


class NodeWithDepth(SimpleNode):
    def __init__(self, state, parent=None, action=None):
        super().__init__(state, parent, action)
        if self.is_root():
            self.depth = 0
        else:
            self.depth = parent.depth + 1

    def __str__(self):
        return 'State: {0}; Prof: {1}'.format(self.state, self.depth)


class NodeWithHeuristic(SimpleNode):
    def __init__(self, state, parent=None, action=None):
        super().__init__(state, parent, action)
        if self.is_root():
            self.depth = 0
            self.cost = 0
        else:
            self.depth = parent.depth + 1
            self.cost = parent.cost + action.cost_of_applying(parent.state)
        self.heuristic = self.f(self)

    @staticmethod
    def f(node):
        return 0

    def __str__(self):
        return 'State: {0}; Prof: {1}; Heur: {2}; Cost: {3}'.format(
            self.state, self.depth, self.heuristic, self.cost)


class GeneralSearch:
    def __init__(self, verbose=False):
        self.verbose = verbose
        if self.verbose:
            self.Node = NodeWithDepth
        else:
            self.Node = SimpleNode
        self.explored = NodeList()

    def is_expansible(self, node):
        return True

    def expand_node(self, node, problem):
        return (node.successor(action)
                for action in problem.applicable_actions(node.state))

    def is_new(self, node):
        return (node not in self.frontier and
                node not in self.explored)

    def search(self, problem):
        self.frontier.empty()
        self.explored.empty()
        self.frontier.add(self.Node(problem.initial_state))
        while True:
            if not self.frontier:
                return None
            node = self.frontier.extract()
            if self.verbose:
                print('{0}Node: {1}'.format('  ' * node.depth, node))
            if problem.is_final_state(node.state):
                return node.solution()
            self.explored.add(node)
            if self.is_expansible(node):
                child_nodes = self.expand_node(node, problem)
                for child_node in child_nodes:
                    if self.is_new(child_node):
                        self.frontier.add(child_node)


class BreadthFirstSearch(GeneralSearch):
    def __init__(self, verbose=False):
        super().__init__(verbose)
        self.frontier = NodeQueue()


class DepthFirstSearch(GeneralSearch):
    def __init__(self, verbose=False):
        super().__init__(verbose)
        self.frontier = NodePile()
        self.explored = NodePile()

        def add_and_empty_branch(self, node):
            if self:
                while True:
                    last_node = self.pop()
                    if last_node == node.parent:
                        self.append(last_node)
                        break
            self.append(node)
        self.explored.add = types.MethodType(add_and_empty_branch,
                                                  self.explored)


class BoundedDepthFirstSearch(DepthFirstSearch):
    def __init__(self, bound, verbose=False):
        super().__init__(verbose)
        self.Node = NodeWithDepth
        self.bound = bound

    def is_expansible(self, node):
        return node.depth < self.bound


class IterativeDepthFirstSearch:
    def __init__(self, bound_final, initial_bound=0, verbose=False):
        self.initial_bound = initial_bound
        self.bound_final = bound_final
        self.verbose = verbose

    def search(self, problem):
        for bound in range(self.initial_bound, self.bound_final):
            bdfs = BoundedDepthFirstSearch(bound, self.verbose)
            solution = bdfs.search(problem)
            if solution:
                return solution


class BestFirstSearch(GeneralSearch):
    def __init__(self, f, verbose=False):
        super().__init__(verbose)
        self.Node = NodeWithHeuristic
        self.Node.f = staticmethod(f)
        self.frontier = NodeQueueWithPriority()
        self.explored = NodeList()
        self.explored.__contains__ = types.MethodType(
            lambda self, node: any(x.state == node.state and
                                   x.heuristic <= node.heuristic
                                   for x in self),
            self.explored)


class OptimalSearch(BestFirstSearch):
    def __init__(self, verbose=False):
        def cost(node):
            return node.cost
        super().__init__(cost, verbose)


class AStarSearch(BestFirstSearch):
    def __init__(self, h, verbose=False):
        def cost(node):
            return node.cost

        def f(node):
            return cost(node) + h(node)
        super().__init__(f, verbose)
