class Solution:
    def __init__(self):
        self.values={}
    def vertical_order(self,root,x,y):
        if root is None :
            return 
        if x in self.values :
            self.values[x].append((y,root.val))
        else :
            self.values[x]=[(y,root.val)]
        self.vertical_order(root.left,x-1,y+1)
        self.vertical_order(root.right,x+1,y+1)   
    def verticalTraversal(self, root: Optional[TreeNode]) -> List[List[int]]:
        self.vertical_order(root,0,0)
        result=[]
        for x in sorted(self.values.keys()):
            column=[i[1] for i in sorted(self.values[x])]
            result.append(column)
        return result
