import ein.core.cdata.eCdata
import ein.core.value.eJsonObject
import ein.core.value.eValue
import ein.core.value.eString
import kotlin.js.Promise
import kotlin.test.Test
import kotlin.test.assertEquals

class CdataTest{
    @Test
    fun step1(){
        eCdata.load(eValue.json("""{
                "cdata":{
                    "@ln":"ko",
                    "@a":"b",
                    "test@ln":{
                        "ko":"안녕하세요.",
                        "en@a":{
                            "b":"hello test"
                        }
                    },
                    "test2@ln":{
                        "ko":"안녕하세요."
                    }
                }
            }""") as eJsonObject)
        assertEquals("ko", eCdata.getCat("ln"))
        assertEquals("b", eCdata.getCat("a"))
        assertEquals("안녕하세요.", eCdata.get<eString>("test")?.v)
        eCdata.setCat("ln", "en")
        assertEquals("en", eCdata.getCat("ln"))
        assertEquals("hello test", eCdata.get<eString>("test")?.v)
        eCdata.setCat("a", "a")
        assertEquals("a", eCdata.getCat("a"))
        assertEquals(null, eCdata.get<eString>("test")?.v)
        assertEquals("test=[ln=en,a=a]", eCdata.requestState)
        assertEquals(null, eCdata.get<eString>("test2")?.v)
        assertEquals("test=[ln=en,a=a]&test2=[ln=en]", eCdata.requestState)
    }
    @Test
    fun step2() = Promise<Boolean>{resolve,_->
        eCdata.requestData {
            eCdata.load(eValue.json("""{
                "cdata":{
                    "test@ln":{
                        "en@a":{
                            "a":"helloA"
                        }
                    },
                    "test2@ln":{
                        "en":"hello"
                    },
                    "test3@ln":{
                        "en":"hello test3"
                    }
                }
            }""") as eJsonObject)
            println("Cdata loaded!")
        }
        resolve(true)
    }.then {
        assertEquals("helloA", eCdata.get<eString>("test")?.v)
        assertEquals("hello", eCdata.get<eString>("test2")?.v)
        assertEquals("hello test3", eCdata.get<eString>("test3")?.v)
    }
}