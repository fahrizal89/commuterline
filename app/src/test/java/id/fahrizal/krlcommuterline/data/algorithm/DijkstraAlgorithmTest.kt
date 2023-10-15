package id.fahrizal.krlcommuterline.data.algorithm

import id.fahrizal.krlcommuterline.data.model.StationDetailResult
import id.fahrizal.krlcommuterline.data.model.StationResult
import org.junit.Assert
import org.junit.Before
import org.junit.Test

internal class DijkstraAlgorithmTest {

    private val dijkstraAlgorithm: DijkstraAlgorithm = DijkstraAlgorithm()

    @Before
    fun init(){
        dijkstraAlgorithm.updateStops(getStops())
    }

    @Test
    fun findRoute(){
        val route = dijkstraAlgorithm.find(0, 4)
        print("nodes: ")
        route.nodes.forEach { node->
            print("${node.id},")
        }
        println()
        println("distance: ${route.distance}")

        //Assertion
        Assert.assertEquals(0, route.nodes[0].id)
        Assert.assertEquals(1, route.nodes[1].id)
        Assert.assertEquals(8, route.nodes[2].id)
        Assert.assertEquals(3, route.nodes[3].id)
        Assert.assertEquals(4, route.nodes[4].id)
    }

    @Test
    fun findRouteWithReverseDirection(){
        //when
        val route = dijkstraAlgorithm.find(4, 0)

        //Assertion
        Assert.assertEquals(4, route.nodes[0].id)
        Assert.assertEquals(3, route.nodes[1].id)
        Assert.assertEquals(8, route.nodes[2].id)
        Assert.assertEquals(1, route.nodes[3].id)
        Assert.assertEquals(0, route.nodes[4].id)
    }

    @Test
    fun findRouteWithWrongStartNode(){
        val route = dijkstraAlgorithm.find(9, 4)

        //then
        Assert.assertEquals(0,route.distance)
    }

    @Test
    fun findRouteWithWrongEndNode(){
        val route = dijkstraAlgorithm.find(0, 9)

        //then
        Assert.assertEquals(0, route.distance)
    }

    @Test
    fun findRouteWithWrongStartNodeAndEndNode(){
        val route = dijkstraAlgorithm.find(9, 10)

        //then
        Assert.assertEquals(0, route.distance)
    }

    private fun getStops(): List<StationResult>{
        return ArrayList<StationResult>().apply {
            add(StationResult(0,"a", HashMap<String, StationDetailResult>().apply { put("1", StationDetailResult(dis = 3));put("7",
                StationDetailResult(dis = 7)
            ) }))
            add(StationResult(1,"b", HashMap<String, StationDetailResult>().apply { put("0", StationDetailResult(dis = 3));put("2",
                StationDetailResult(dis = 7)
            );put("7", StationDetailResult(dis = 10));put("8", StationDetailResult(dis = 4)) }))
            add(StationResult(2,"c", HashMap<String, StationDetailResult>().apply { put("1", StationDetailResult(dis = 7));put("3",
                StationDetailResult(dis = 6)
            );put("5", StationDetailResult(dis = 2));put("8", StationDetailResult(dis = 1))}))
            add(StationResult(3,"d", HashMap<String, StationDetailResult>().apply { put("2", StationDetailResult(dis = 6));put("4",
                StationDetailResult(dis = 8)
            );put("5", StationDetailResult(dis = 13));put("8", StationDetailResult(dis = 3))}))
            add(StationResult(4,"e", HashMap<String, StationDetailResult>().apply { put("3", StationDetailResult(dis = 8));put("5",
                StationDetailResult(dis = 9)
            )}))
            add(StationResult(5,"f", HashMap<String, StationDetailResult>().apply { put("2", StationDetailResult(dis = 2));put("3",
                StationDetailResult(dis = 13)
            );put("4", StationDetailResult(dis = 9));put("6", StationDetailResult(dis = 4));put("8",
                StationDetailResult(dis = 5)
            )}))
            add(StationResult(6,"g", HashMap<String, StationDetailResult>().apply { put("5", StationDetailResult(dis = 4));put("7",
                StationDetailResult(dis = 2)
            );put("8", StationDetailResult(dis = 5))}))
            add(StationResult(7,"h", HashMap<String, StationDetailResult>().apply { put("0", StationDetailResult(dis = 7));put("1",
                StationDetailResult(dis = 10)
            );put("6", StationDetailResult(dis = 2));put("8", StationDetailResult(dis = 6))}))
            add(StationResult(8,"i", HashMap<String, StationDetailResult>().apply { put("1", StationDetailResult(dis = 4));put("2",
                StationDetailResult(dis = 1)
            );put("3", StationDetailResult(dis = 3));put("5", StationDetailResult(dis = 5));put("6",
                StationDetailResult(dis = 5)
            );put("7", StationDetailResult(dis = 6))}))
        }
    }
}