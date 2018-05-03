package android1.myapplication1

import org.junit.Test
import kotlin.test.assertEquals


/**
 * Created by stan.
 */
class ArrTest {


  @Test
  fun checkFun01() {
    val intArrayOf = intArrayOf(2, 7, 11, 15)
    val targetSum = 9

    assert(targetSum == twoSum(intArrayOf, targetSum).sum())
    assertEquals(intArrayOf(0, 1).indices, twoSum(intArrayOf, targetSum).indices)
  }

  @Test
  fun checkFun02() {
    println(arrayOf(1, 1, 2).distinct().size)
  }

  @Test
  fun checkRevertInt() {
    val int = -120
    assertEquals(-21, reverse(int))
  }

  @Test
  fun checkPalindromeInt() {
    val int = -120
    assert(isPalindrome(int))
  }

  fun reverse(x1: Int): Int {
    var x = x1
    var result = 0

    while (x != 0) {
      val tail = x % 10
      val newResult = result * 10 + tail
      if ((newResult - tail) / 10 != result) {
        return 0
      }
      result = newResult
      x = x / 10
    }

    return result
  }

  fun isPalindrome(x1: Int): Boolean {
    return reverse(x1) == x1
  }
}

fun twoSum(nums: IntArray, target: Int): IntArray {
  (0..nums.size - 2).forEach {
    (it + 1 until nums.size).forEach { j ->
      // println("nums data ${nums[it]} " )
      if (nums[it] + nums[j] == target) return intArrayOf(it, j)
    }
  }
  throw IllegalArgumentException()
}

}