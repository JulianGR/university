class Node:
    # def __init__(self, value, parent, left, right):
    #     self.value = value
    #     self.parent = parent
    #     self.left = left
    #     self.right = right

    def __init__(self, value, node_id):
        self.value = value
        self.id = node_id
        self.parent = None
        self.left = None
        self.right = None

    def set_left(self, left):
        self.left = left
        self.left.parent = self

    def set_right(self, right):
        self.right = right
        self.right.parent = self

    def is_leaf(self):
        return self.left is None and self.right is None
