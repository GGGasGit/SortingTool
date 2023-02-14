fun helpingTheRobot(purchases: Map<String, Int>, addition: Map<String, Int>): MutableMap<String, Int> {
    val finalList = purchases.toMutableMap()
    for ((product, number) in addition) {
        if (finalList.containsKey(product)) {
            finalList[product] = finalList[product]!! + number
        } else {
            finalList[product] = number
        }
    }
    return finalList
}