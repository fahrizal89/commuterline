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
        dijkstraAlgorithm.updateStations(getStations())
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

    private fun getStations(): List<StationResult>{
        return ArrayList<StationResult>().apply {
            add(StationResult(0,"a", HashMap<String, StationDetailResult>().apply { put("1", StationDetailResult(distance = 3));put("7",
                StationDetailResult(distance = 7)
            ) }))
            add(StationResult(1,"b", HashMap<String, StationDetailResult>().apply { put("0", StationDetailResult(distance = 3));put("2",
                StationDetailResult(distance = 7)
            );put("7", StationDetailResult(distance = 10));put("8", StationDetailResult(distance = 4)) }))
            add(StationResult(2,"c", HashMap<String, StationDetailResult>().apply { put("1", StationDetailResult(distance = 7));put("3",
                StationDetailResult(distance = 6)
            );put("5", StationDetailResult(distance = 2));put("8", StationDetailResult(distance = 1))}))
            add(StationResult(3,"d", HashMap<String, StationDetailResult>().apply { put("2", StationDetailResult(distance = 6));put("4",
                StationDetailResult(distance = 8)
            );put("5", StationDetailResult(distance = 13));put("8", StationDetailResult(distance = 3))}))
            add(StationResult(4,"e", HashMap<String, StationDetailResult>().apply { put("3", StationDetailResult(distance = 8));put("5",
                StationDetailResult(distance = 9)
            )}))
            add(StationResult(5,"f", HashMap<String, StationDetailResult>().apply { put("2", StationDetailResult(distance = 2));put("3",
                StationDetailResult(distance = 13)
            );put("4", StationDetailResult(distance = 9));put("6", StationDetailResult(distance = 4));put("8",
                StationDetailResult(distance = 5)
            )}))
            add(StationResult(6,"g", HashMap<String, StationDetailResult>().apply { put("5", StationDetailResult(distance = 4));put("7",
                StationDetailResult(distance = 2)
            );put("8", StationDetailResult(distance = 5))}))
            add(StationResult(7,"h", HashMap<String, StationDetailResult>().apply { put("0", StationDetailResult(distance = 7));put("1",
                StationDetailResult(distance = 10)
            );put("6", StationDetailResult(distance = 2));put("8", StationDetailResult(distance = 6))}))
            add(StationResult(8,"i", HashMap<String, StationDetailResult>().apply { put("1", StationDetailResult(distance = 4));put("2",
                StationDetailResult(distance = 1)
            );put("3", StationDetailResult(distance = 3));put("5", StationDetailResult(distance = 5));put("6",
                StationDetailResult(distance = 5)
            );put("7", StationDetailResult(distance = 6))}))
        }
    }
}