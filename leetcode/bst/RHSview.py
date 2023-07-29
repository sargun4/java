
class Solution:
    def rightSideView(self, root: Optional[TreeNode]) -> List[int]:
        res=[]
        q=collections.deque([root])
        while q:
            rightside=None
            qlen=len(q)
            for i in range(qlen):
                node=q.popleft()
                if(node):
                    rightside=node
                    q.append(node.left)
                    q.append(node.right)
            if(rightside):
                res.append(rightside.val)
        return res


# class Solution:
#     def rightSideView(self, root: Optional[TreeNode]) -> List[int]:
#         if not root:
#             return []
#         res = []
#         queue = [root]
#         while queue:
#             res.append(queue[-1].val)
#             level_size = len(queue)
#             for _ in range(level_size):
#                 node = queue.pop(0)
#                 if node.left:
#                     queue.append(node.left)
#                 if node.right:
#                     queue.append(node.right)
#         return res


# class Solution:
#     def lvlorder(self,root):
#         if not root:
#             return []
#         res = []
#         q = []
#         q.append(root)
#         while q:
#             level_vals = []
#             for i in range(len(q)):
#                 node = q.pop(0)
#                 if node is None:
#                     level_vals.append(None)
#                     continue
#                 level_vals.append(node.val)
#                 if node.left:
#                     q.append(node.left)
#                 else:
#                     q.append(None)
#                 if node.right:
#                     q.append(node.right)
#                 else:
#                     q.append(None)
#             res.append(level_vals)
#         return res

#     def rightSideView(self, root: Optional[TreeNode]) -> List[int]:
#         res=self.lvlorder(root)
#         print(res)
#         l=[]
#         for i in res:
#             if(i[-1]!=None):
#                 l.append(i[-1])
#             else:
#                 # l.append(i[x] for x in range(len(i)) if x is not None)
#                 l.extend([x for x in i if x is not None])
#         return l
# class Solution:
#     def rightSideView(self, root: Optional[TreeNode]) -> List[int]:
#         def solve(root, lvl):
#             if root:
#                 if len(res)==lvl:
#                     res.append(root.val)
#                 solve(root.right, lvl + 1)
#                 solve(root.left, lvl + 1)
#             return 
#         res = []
#         solve(root,0)
#         return res
