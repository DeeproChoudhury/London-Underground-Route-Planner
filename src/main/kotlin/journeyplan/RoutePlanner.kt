package journeyplan

// Add your code for the route planner in this file.
class Station (private val name:String) {
    override fun toString(): String {
        return name
    }
}

class Line (private val lineName: String) {
    override fun toString(): String {
        return "$lineName Line"
    }
}

class Segment (val station1:Station, val station2:Station, linkedLine:Line, time:Int) {

}
class SubwayMap (val map:List<Segment>) {
    fun routesFrom(origin: Station, destination: Station): List<Route> {
        fun helper(currentSegment: Segment, visitedStations: List<Segment>): List<Segment> {
            val segments = map.filter { x -> x.station1 == currentSegment.station2 && x.station2 !in visitedStations }
            visitedStations.plus(currentSegment.station2)
            for (segment in segments) {
                return if (segment.station1 == destination) {
                    emptyList()
                } else {
                    listOf(currentSegment).plus(helper(segment, visitedStations))
                }
            } ; return listOf(currentSegment)
        }
    }

}

fun londonUnderground(): SubwayMap {
    val actonT = Station("Acton Town")
    val aldgate = Station("Aldgate")
    val aldgateE = Station("Aldgate East")
    val alperton = Station("Alperton")
    val amersham = Station("Amersham")
    val angel = Station("Angel")
    val highgate = Station("Highgate")
    val archway = Station("Archway")
    val tufnellPark = Station("Tufnell Park")
    val kentishTown = Station("Kentish Town")
    val knightsbridge = Station("Knightsbridge")
    val greenPark = Station("Green Park")
    val hydeParkCorner = Station("Hyde Park Corner")
    val victoria = Station("Victoria")
    val sloaneSquare = Station("Sloane Square")
    val southKensington = Station("South Kensington")
    val camden = Station("Camden Town")
    val euston = Station("Euston")
    val warrenStreet = Station("Warren Street")
    val oxfordCircus = Station("Oxford Circus")
    val bondStreet = Station("Bond Street")
    val SK = Station("South Kensington")

    val northernLine = Line("Northern")
    val victoriaLine = Line("Victoria")
    val centralLine = Line("Central")
    val piccadillyLine = Line("Piccadilly")
    val districtLine = Line("District")

    var subwaymap = SubwayMap(listOf(
            Segment(highgate, camden, northernLine, 3),
            Segment(knightsbridge, hydeParkCorner, piccadillyLine, 4),
            Segment(hydeParkCorner, greenPark, piccadillyLine, 2),
            Segment(greenPark, oxfordCircus, victoriaLine, 1),
            Segment(greenPark, victoria, victoriaLine, 1),
            Segment(victoria, greenPark, victoriaLine, 1),
            Segment(victoria, sloaneSquare, districtLine, 6),
            Segment(sloaneSquare, southKensington, districtLine, 3),
            Segment(southKensington, sloaneSquare, districtLine, 6),
            Segment(sloaneSquare, victoria, districtLine, 6)
        )
    )
    return subwaymap

}

class Route(val segments: List<Segment>) {
    override fun toString(): String {
        val overview = "${segments.first().station1} to ${segments.last().station2}\n"
        return overview + segments.joinToString(prefix = "", postfix = "", separator = "\n")
    }

}

fun main () {

    val northernLine = Line("Northern")
    val victoriaLine = Line("Victoria")
    val centralLine = Line("Central")

    val highgate = Station("Highgate")
    val archway = Station("Archway")
    val tufnellPark = Station("Tufnell Park")
    val kentishTown = Station("Kentish Town")
    val camden = Station("Camden Town")
    val euston = Station("Euston")
    val warrenStreet = Station("Warren Street")
    val oxfordCircus = Station("Oxford Circus")
    val bondStreet = Station("Bond Street")

    var subwaymap = londonUnderground()





}