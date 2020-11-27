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
    var state = "normal"
    fun suspend(): String {
        state = "suspended"
        return state
    }

    fun resume(): String {
        state = "normal"
        return state
    }

}


class Segment (val station1:Station, val station2:Station, val line:Line, val time:Int) {

}
class SubwayMap (val map:List<Segment>) {
    fun routesFrom(origin: Station, destination: Station, optimisingFor: (Route) -> Int = Route :: duration): List<Route> {
        val listRoutes = helper(origin, destination, emptySet())
        return listRoutes.sortedBy(optimisingFor)
    }
    private fun helper(origin: Station, destination: Station, visitedStations: Set<Station>): List<Route> {
        if (origin == destination) {
            return listOf(Route(emptyList()))
        }
        val segments = map.filter { x -> x.station1 == origin && x.station2 !in visitedStations && x.line.state != "suspended"}

        return segments.flatMap { segment -> helper(segment.station2, destination, visitedStations.plus(origin)).map {Route (listOf(segment) + it.segments)}}
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
    println(subwaymap.routesFrom(victoria, greenPark).first())
    return subwaymap

}

class Route(val segments: List<Segment>) {
    override fun toString(): String {
        val sb = StringBuilder ()
        sb.append("${segments.first().station1} to ${segments.last().station2} - ${duration()} minutes, ${numChanges()} changes\n")
        var currentLine = segments.first().line
        sb.append(" - ${segments.first().station1} to ")
        for (segment in segments) {
            if (segment.line != currentLine) {
                sb.append("${segment.station1} by $currentLine\n")
                sb.append(" - ${segment.station1} to ")
                currentLine = segment.line
            }
        }
        sb.append("${segments.last().station2} by $currentLine")
        return sb.toString()
    }

    fun duration () : Int {
        return segments.map {it.time}.sum()
    }

    fun numChanges (): Int {
        return segments.zipWithNext().filter { (x, y) -> x.line != y.line }.size
    }
}

fun main () {


    londonUnderground()





}