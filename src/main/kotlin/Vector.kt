class Vector(var x: Double, var y: Double) {

    fun reverseX() {
        x *= -1
    }

    fun reverseY() {
        y *= -1
    }

    fun add(vector: Vector) {

    }

    override fun toString(): String {
        return "x=$x y=$y"
    }


}
