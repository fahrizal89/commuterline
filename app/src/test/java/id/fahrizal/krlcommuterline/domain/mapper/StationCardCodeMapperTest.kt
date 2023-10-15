package id.fahrizal.krlcommuterline.domain.mapper

import id.fahrizal.krlcommuterline.domain.mapper.StationCardCodeMapper.filterDestinations
import id.fahrizal.krlcommuterline.domain.model.StationCard
import id.fahrizal.krlcommuterline.domain.model.StationCardBranch
import org.junit.Assert
import org.junit.Test

internal class StationCardCodeMapperTest{
    
    @Test
    fun filter_stations_with_transit_at_index4() {
        //given
        val StationCards = getStationCards()

        //when
        StationCards.filterDestinations()

        //then
        //assert group index
        Assert.assertEquals(3, StationCards[0].groupIndex)
        Assert.assertEquals(3, StationCards[1].groupIndex)
        Assert.assertEquals(3, StationCards[2].groupIndex)
        Assert.assertEquals(3, StationCards[3].groupIndex)
        Assert.assertEquals(4, StationCards[4].groupIndex)
    }

    @Test
    fun filter_station_with_best_stationCodes() {
        //given
        val StationCards = getStationCards()

        //when
        StationCards.filterDestinations()

        //then
        //assert user using stationCode "2" only at first time, instead of "2" and "2A"
        Assert.assertEquals(1, StationCards[0].next.stationCodes.size)
        Assert.assertEquals("2", StationCards[0].next.stationCodes[0])

        //assert index 3 next station will be transit station, then stationCode should be "1"
        Assert.assertEquals("1", StationCards[3].next.stationCodes[0])
    }

    private fun getStationCards() : List<StationCard> {
        return ArrayList<StationCard>().apply {
            add(
                StationCard(0, 0,"Pondok Ranji", next = StationCardBranch(1, ArrayList<String>().apply { add("2");add("2A") }))
            )

            add(
                StationCard(1, 1,"Kebayoran", next = StationCardBranch(2, ArrayList<String>().apply { add("2");add("2A") }))
            )

            add(
                StationCard(2, 2,"Palmerah", next = StationCardBranch(2, ArrayList<String>().apply { add("2") }))
            )

            add(
                StationCard(3, 3,"Tanah Abang", next = StationCardBranch(2, ArrayList<String>().apply { add("1") }))
            )

            add(
                StationCard(4, 4,"Karet", next = StationCardBranch())
            )
        }
    }
}