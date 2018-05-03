package android1.myapplication1

import android1.myapplication1.data.model.ComicsEntity
import java.util.concurrent.ThreadLocalRandom
import kotlin.reflect.KClass


object StubUtils {
  fun randomUuid(): String {
    return java.util.UUID.randomUUID().toString()
  }

  fun randomInt(): Int {
    return ThreadLocalRandom.current().nextInt(0, 1000 + 1)
  }

  fun randomBoolean(): Boolean {
    return Math.random() < 0.5
  }

  fun makeStringList(count: Int): List<String> {
    val items: MutableList<String> = mutableListOf()
    repeat(count) {
      items.add(randomUuid())
    }
    return items
  }


  fun makeList(count: Int): List<ComicsEntity> {
    val bufferoos = mutableListOf<ComicsEntity>()
    repeat(count) {
      bufferoos.add(makeModel(ComicsEntity::class))
    }
    return bufferoos
  }

  // can be factory with T as Stub class
  fun makeModel(kClass: KClass<ComicsEntity>): ComicsEntity {
    val builder = factories[kClass]
    if (builder != null) {
      return builder()
    }
    throw UnsupportedOperationException("${kClass.qualifiedName} is not supported!")
  }

  private val factories = mapOf(
      ComicsEntity::class to { ComicsEntity(randomInt(), randomUuid()) }
  )
}