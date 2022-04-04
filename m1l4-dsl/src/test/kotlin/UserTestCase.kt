import org.junit.Test
import user.dsl.user
import user.models.Action
import kotlin.test.assertEquals

class UserTestCase {
    @Test
    fun `test User`() {
        val user = user {
            name {
                first = "Kirill"
                last = "Krylov"
            }
            contacts {
                email = "email@gmail.com"
                phone = "81234567890"
            }
            actions {
                add(Action.UPDATE)
                add(Action.ADD)

                +Action.DELETE
                +Action.READ
            }
            availability {
                monday("11:30")
                friday("18:00")
            }
        }

        assertEquals("class user.models.User", user.javaClass.toString())
        assertEquals("Kirill", user.firstName)
    }
}