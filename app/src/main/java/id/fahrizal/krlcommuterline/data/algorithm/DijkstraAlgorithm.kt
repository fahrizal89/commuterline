package id.fahrizal.krlcommuterline.data.algorithm

import id.fahrizal.krlcommuterline.data.model.Route
import id.fahrizal.krlcommuterline.data.model.StationResult
import java.util.Stack
import javax.inject.Inject

class DijkstraAlgorithm @Inject constructor(){

    val graph: ArrayList<StationResult> = ArrayList()

    fun updateStations(graph: List<StationResult>){
        this.graph.clear()
        this.graph.addAll(graph)
    }

    fun find(startNode: Int, endNode:Int): Route {
        val totalVertex= graph.size
        val distance = IntArray(totalVertex)
        val spSet = arrayOfNulls<Boolean>(totalVertex)
        val prev = HashMap<Int, Int>()

        //handling when start node is not found
        if(startNode > totalVertex-1) {
            return Route()
        }

        for (j in 0 until totalVertex) {
            distance[j] = Int.MAX_VALUE
            spSet[j] = false
        }

        // Distance from the source vertex to itself is always 0
        distance[startNode] = 0
        //set startNode without previous node
        prev[startNode] = -1

        for (cnt in 0 until totalVertex - 1) {
            val ux = minimumDistance(distance, spSet, totalVertex)

            spSet[ux] = true

            for (vx in 0 until totalVertex) {
                val vxKey = vx.toString()

                val nodeDistance :Int = graph[ux].branch?.get(vxKey)?.distance ?: -1

                if (!spSet[vx]!! && graph[ux].branch?.get(vxKey)?.distance != -1 && graph[ux].branch?.get(vxKey) != null && distance[ux] != Int.MAX_VALUE && distance[ux] + nodeDistance < distance[vx]) {
                    distance[vx] = distance[ux] + nodeDistance

                    //println("prev: $ux -> visit: $vx with total distance: "+distance[vx])
                    prev[vx] = ux
                }
            }
        }

        val nodeOfRoute = getNodeOfRoute(prev, endNode)

        return Route(
            nodes = nodeOfRoute,
            distance = if(nodeOfRoute.isNotEmpty()) distance[endNode] else 0
        )
    }

    private fun minimumDistance(distance: IntArray, spSet: Array<Boolean?>, totalVertex:Int): Int {
        var m = Int.MAX_VALUE
        var index = -1
        for (vx in 0 until totalVertex) {
            if (spSet[vx] == false && distance[vx] <= m) {
                m = distance[vx]
                index = vx
            }
        }
        return index
    }

    private fun getNodeOfRoute(prev: HashMap<Int, Int>, endNode: Int): List<StationResult> {
        if(endNode >= graph.size) return ArrayList()

        val route = Stack<StationResult>()
        route.add(graph[endNode])

        var pointedNode :Int= endNode
        var i = 0
        while (i < 1000){
            prev[pointedNode]?.let { prevValue ->

                //if prevValue without previous node
                if(prevValue == -1) return  route.reversed()

                //add node to route
                route.add(graph[prevValue])
                pointedNode = prevValue
            }
            i++
        }

        return ArrayList()
    }
}