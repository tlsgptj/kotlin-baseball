import java.lang.IllegalArgumentException

class Baseball {
    private val computerNumbers: List<Int> = (1..9).shuffled().take(3)

    fun play() {
        println("게임을 시작합니다!")

        while (true) {
            println("숫자를 입력해주세요:")
            val input = readLine() ?: ""
            try {
                validateInput(input)
                val userNumbers = input.map { it.toString().toInt() }
                val result = compareNumbers(userNumbers)
                println(result)

                if (result == "3스트라이크") {
                    println("3개의 숫자를 모두 맞히셨습니다! 게임 종료")
                    if (playAgain()) {
                        println("새로운 게임을 시작합니다!")
                        break
                    } else {
                        println("게임을 종료합니다.")
                        return
                    }
                }
            } catch (e: IllegalArgumentException) {
                println("${e.message} 다시 입력해주세요.")
            } catch (e: NumberFormatException) {
                println("잘못된 입력입니다. 다시 입력해주세요.")
            }
        }
    }

    private fun validateInput(input: String) {
        if (input.length != 3 || input.any { !it.isDigit() }) {
            throw IllegalArgumentException("입력은 3자리의 숫자여야 합니다.")
        }
    }

    private fun compareNumbers(userNumbers: List<Int>): String {
        var strikes = 0
        var balls = 0

        for ((index, number) in userNumbers.withIndex()) {
            if (number == computerNumbers[index]) {
                strikes++
            } else if (number in computerNumbers) {
                balls++
            }
        }

        return when {
            strikes == 3 -> "3스트라이크"
            strikes > 0 && balls > 0 -> "$balls 볼 $strikes 스크라이크"
            strikes > 0 -> "$strikes 스크라이크"
            balls > 0 -> "$balls 볼"
            else -> "낫싱"
        }
    }

    private fun playAgain(): Boolean {
        println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.")
        val input = readLine() ?: ""
        return input == "1"
    }
}

fun main() {
    val baseballGame = Baseball()
    baseballGame.play()
}