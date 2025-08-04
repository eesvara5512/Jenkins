def country = "US"
def env = "t01"


if (country == "US" && env == "t01") {
    println "picking us cluster"
} else if ((country == "AU" || country == "AUS") && env == t01) {
    println "picking t01 cluster"
} else if ((country == "AU" || country == "AUS") && env == t02) {
    println "picking t02 cluster"
}
