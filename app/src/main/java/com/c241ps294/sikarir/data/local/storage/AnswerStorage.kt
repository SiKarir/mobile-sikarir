package com.c241ps294.sikarir.data.local.storage

object AnswerStorage {
    private val answers: MutableMap<Int, String> = mutableMapOf()

    fun addOrUpdateAnswer(key: Int, answer: String) {
        answers[key] = answer
    }

//    fun getAnswers(): HashMap<Int, String> {
//
//        return HashMap(answers)
//    }

    fun getAnswers(): HashMap<Int, String> {
        val answers = hashMapOf<Int, String>()
        answers[1] = "4"
        answers[2] = "1"
        answers[3] = "3"
        answers[4] = "5"
        answers[5] = "2"
        answers[6] = "1"
        answers[7] = "4"
        answers[8] = "5"
        answers[9] = "2"
        answers[10] = "3"
        answers[11] = "3"
        answers[12] = "2"
        answers[13] = "4"
        answers[14] = "1"
        answers[15] = "5"
        answers[16] = "5"
        answers[17] = "1"
        answers[18] = "3"
        answers[19] = "2"
        answers[20] = "4"
        answers[21] = "4"
        answers[22] = "3"
        answers[23] = "2"
        answers[24] = "1"
        answers[25] = "5"
        answers[26] = "2"
        answers[27] = "4"
        answers[28] = "5"
        answers[29] = "1"
        answers[30] = "3"
        answers[31] = "1"
        answers[32] = "5"
        answers[33] = "2"
        answers[34] = "4"
        answers[35] = "3"
        answers[36] = "4"
        answers[37] = "5"
        answers[38] = "2"
        answers[39] = "1"
        answers[40] = "3"
        answers[41] = "3"
        answers[42] = "4"
        answers[43] = "1"
        answers[44] = "2"
        answers[45] = "5"
        answers[46] = "5"
        answers[47] = "3"
        answers[48] = "4"
        answers[49] = "1"
        answers[50] = "2"
        answers[51] = "B"
        answers[52] = "B"
        answers[53] = "B"
        answers[54] = "B"
        answers[55] = "B"
        answers[56] = "B"
        answers[57] = "B"
        answers[58] = "B"
        answers[59] = "B"
        answers[60] = "B"
        answers[61] = "B"
        answers[62] = "B"
        answers[63] = "B"
        answers[64] = "B"
        answers[65] = "B"
        answers[66] = "B"
        answers[67] = "B"
        answers[68] = "B"
        answers[69] = "B"
        answers[70] = "B"
        answers[71] = "B"
        answers[72] = "B"
        answers[73] = "B"
        answers[74] = "B"
        answers[75] = "B"
        answers[76] = "B"
        answers[77] = "B"
        answers[78] = "B"
        answers[79] = "B"
        answers[80] = "B"
        answers[81] = "B"
        answers[82] = "B"
        answers[83] = "B"
        answers[84] = "B"
        answers[85] = "B"
        answers[86] = "B"
        answers[87] = "B"
        answers[88] = "B"
        answers[89] = "B"
        answers[90] = "B"
        answers[91] = "B"
        answers[92] = "B"
        answers[93] = "B"
        answers[94] = "B"
        answers[95] = "B"
        answers[96] = "B"
        answers[97] = "B"
        answers[98] = "B"
        answers[99] = "B"
        answers[100] = "B"
        return answers
    }

    fun clearAnswers() {
        answers.clear()
    }
}
