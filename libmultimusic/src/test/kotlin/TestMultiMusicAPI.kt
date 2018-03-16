import com.cg.libmultimusic.MultiMusicAPI
import kotlinx.coroutines.experimental.launch
import org.junit.Test

class TestMultiMusicAPI {

    @Test
    fun testSearch(){
        launch {
            val result = MultiMusicAPI.search("hello", MultiMusicAPI.ServiceType.NETEASE)
            assert(result.code == 200)

            val result2 = MultiMusicAPI.nextPage()
            assert(result2.code == 200)

            val result3 = MultiMusicAPI.previousPage()
            assert(result3.code == 200)

        }

    }
}