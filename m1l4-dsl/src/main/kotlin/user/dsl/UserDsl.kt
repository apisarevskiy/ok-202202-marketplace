package user.dsl

import user.models.Action
import user.models.User
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.temporal.TemporalAdjusters
import java.util.UUID

@UserDsl
class NameContext {
    var first: String = ""
    var second: String = ""
    var last: String = ""
}

@UserDsl
class ContactContext {
    var email: String = ""
    var phone: String = ""
}

@UserDsl
class ActionsContext {

    private var _actions: MutableSet<Action> = mutableSetOf()

    val actions: Set<Action>
        get() = _actions.toSet()

    fun add(action: Action) = _actions.add(action)

    fun add(value: String) = add(Action.valueOf(value))

    operator fun Action.unaryPlus() = add(this)

    operator fun String.unaryPlus() = add(this)
}

@UserDsl
class AvailabilityContext {

    private var _availabilities: MutableList<LocalDateTime> = mutableListOf()

    val availabilities: List<LocalDateTime>
        get() = _availabilities.toList()

    fun sunday(time: String) = dyaTimeOfWeek(DayOfWeek.SUNDAY, time)
    fun monday(time: String) = dyaTimeOfWeek(DayOfWeek.MONDAY, time)
    fun tuesday(time: String) = dyaTimeOfWeek(DayOfWeek.TUESDAY, time)
    fun wednesday(time: String) = dyaTimeOfWeek(DayOfWeek.WEDNESDAY, time)
    fun thursday(time: String) = dyaTimeOfWeek(DayOfWeek.THURSDAY, time)
    fun friday(time: String) = dyaTimeOfWeek(DayOfWeek.FRIDAY, time)
    fun saturday(time: String) = dyaTimeOfWeek(DayOfWeek.SATURDAY, time)

    private fun dyaTimeOfWeek(day: DayOfWeek, time: String) {
        val dDay = LocalDate.now().with(TemporalAdjusters.next(day))
        val dTime = time.split(":")
            .map { it.toInt() }
            .let { LocalTime.of(it[0], it[1]) }

        _availabilities.add(LocalDateTime.of(dDay, dTime))
    }
}

@UserDsl
class UserBuilder {

    var id = UUID.randomUUID().toString()

    var firstName = ""
    var secondName = ""
    var lastName = ""

    var phone = ""
    var email = ""

    var actions = emptySet<Action>()

    var available = emptyList<LocalDateTime>()

    @UserDsl
    fun name(block: NameContext.() -> Unit) {
        val ctx = NameContext().apply(block)

        firstName = ctx.first
        secondName = ctx.second
        lastName = ctx.last
    }

    @UserDsl
    fun contacts(block: ContactContext.() -> Unit) {
        val ctx = ContactContext().apply(block)

        phone = ctx.phone
        email = ctx.email
    }

    @UserDsl
    fun actions(block: ActionsContext.() -> Unit) {
        val ctx = ActionsContext().apply(block)

        actions = ctx.actions
    }

    @UserDsl
    fun availability(block: AvailabilityContext.() -> Unit) {
        val ctx = AvailabilityContext().apply(block)

        available = ctx.availabilities
    }

    @UserDsl
    fun build() = User( id, firstName, secondName, lastName, phone, email, actions, available )
}

@UserDsl1
fun user(block: UserBuilder.() -> Unit) = UserBuilder().apply(block).build()

@DslMarker
annotation class UserDsl

@DslMarker
annotation class UserDsl1