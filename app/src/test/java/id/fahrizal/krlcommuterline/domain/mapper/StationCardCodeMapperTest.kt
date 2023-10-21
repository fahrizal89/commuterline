package id.fahrizal.krlcommuterline.domain.mapper

import id.fahrizal.krlcommuterline.domain.mapper.StationCardCodeMapper.filterDestinationAndSetColor
import id.fahrizal.krlcommuterline.domain.model.StationCard
import id.fahrizal.krlcommuterline.domain.model.StationCardBranch
import org.junit.Assert
import org.junit.Test

internal class StationCardCodeMapperTest{
    
    @Test
    fun filter_stations_with_transit_at_index4() {
        //given
        val stationCards = getStationCards()

        //when
        stationCards.filterDestinationAndSetColor()

        //then
        //assert group index
        Assert.assertEquals(4, stationCards[0].groupIndex)
        Assert.assertEquals(4, stationCards[1].groupIndex)
        Assert.assertEquals(5, stationCards[2].groupIndex)
        Assert.assertEquals(5, stationCards[3].groupIndex)
        Assert.assertEquals(5, stationCards[4].groupIndex)
    }

    @Test
    fun firstStation_have_destinationName_and_only1Destination() {
        //given
        val stationCards = getStationCards()

        //when
        stationCards.filterDestinationAndSetColor()

        //then
        Assert.assertEquals(1, stationCards[0].next.stationCodes.size)
        Assert.assertEquals("2-Tanah Abang", stationCards[0].next.stationCodes[0])
    }

    @Test
    fun filter_station_with_best_stationCodes() {
        //given
        val stationCards = getStationCards()

        //when
        stationCards.filterDestinationAndSetColor()

        //then
        Assert.assertEquals(1, stationCards[1].next.stationCodes.size)
        Assert.assertEquals("1-Parung Panjang", stationCards[1].next.stationCodes[0])
    }

    @Test
    fun filter_station_shouldHave_2_stationCodes() {
        //given
        val stationCards = getStationCards()
        (stationCards as ArrayList).removeAt(stationCards.lastIndex)

        //when
        stationCards.filterDestinationAndSetColor()

        //then
        Assert.assertEquals(2, stationCards[1].next.stationCodes.size)
        Assert.assertEquals("1-Serpong", stationCards[1].next.stationCodes[0])
        Assert.assertEquals("1-Parung Panjang", stationCards[1].next.stationCodes[1])
    }

    private fun getStationCards() : List<StationCard> {
        return ArrayList<StationCard>().apply {
            add(
                StationCard(0, 0,"Karet", next = StationCardBranch(5, "Tanah Abang",ArrayList<String>().apply { add("2-Tanah Abang") }))
            )

            add(
                StationCard(1, 1,"Tanah Abang", next = StationCardBranch(4, "Serpong",ArrayList<String>().apply { add("1-Serpong");add("1-Parung Panjang") }))
            )

            add(
                StationCard(2, 2,"Palmerah", next = StationCardBranch(3, "Serpong",ArrayList<String>().apply { add("1-Serpong");add("1-Parung Panjang") }))
            )

            add(
                StationCard(3, 3,"Serpong", next = StationCardBranch(2, "Serpong",ArrayList<String>().apply { add("1-Serpong");add("1-Parung Panjang")}))
            )

            add(
                StationCard(4, 4,"Parung Panjang", next = StationCardBranch(1, "Parung Panjang",ArrayList<String>().apply { add("1-Parung Panjang") }))
            )




        }
    }
}