############
#
#
# Written by Lewis Kim.
#





#
class Vertex:

	"""."""
	def __init__(self, label):
		self.label = label

		self.connected_vertices = []


#
class Edge:

	"""."""
	def __init__(self, v1, v2, cost, label=""):
		if type(cost) != float or type(cost) != int:
			raise ValueError("Edge costs must be numeric.")

		# Check type for v1, v2

		self.v1 = v1
		self.v2 = v2
		self.cost = cost
		self.label = label