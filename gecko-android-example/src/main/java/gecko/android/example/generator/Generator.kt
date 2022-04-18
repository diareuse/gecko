package gecko.android.example.generator

internal interface Generator<Type> {

    fun generate(): Type

}