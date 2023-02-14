fun removing(currentMap: MutableMap<Int, String>, value: String): MutableMap<Int, String> {
    return currentMap.filter { it.value != value }.toMutableMap()
}