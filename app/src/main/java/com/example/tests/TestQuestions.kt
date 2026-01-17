package com.example.tests

object TestQuestions {

    data class Question(
        val question: String,
        val options: List<String>,
        val correctAnswer: String
    )

    private val questionsMap = mapOf(
        "Test1" to mapOf(
            "english" to listOf(
                Question("What is the past tense of 'go'?", listOf("goed", "went", "gone", "going"), "went"),
                Question("Choose the correct article: ___ apple.", listOf("A", "An", "The", "None"), "An"),
                Question("Which word is a noun?", listOf("run", "happy", "dog", "quickly"), "dog"),
                Question("Which word is an adjective?", listOf("run", "happy", "dog", "quickly"), "happy"),
                Question("Identify the verb in the sentence: 'She quickly ran to the store.'", listOf("quickly", "ran", "store", "she"), "ran"),
                Question("Which of these sentences is correct?", listOf("She don't like apples.", "She doesn't like apples.", "She isn't like apples.", "She not like apples."), "She doesn't like apples.")
            ),
            "polish" to listOf(
                Question("Jakie jest przeciwieństwo słowa 'wysoki'?", listOf("duży", "niski", "szybki", "wolny"), "niski"),
                Question("Wybierz poprawną formę: On ___ książkę.", listOf("czytać", "czyta", "czytam", "czytasz"), "czyta"),
                Question("Które słowo to czasownik?", listOf("słońce", "piękny", "biegać", "dom"), "biegać"),
                Question("Jakie jest poprawne zdanie?", listOf("On chodził do szkoły codziennie.", "On chodzi codziennie do szkoły.", "On chodziła do szkoły codziennie.", "On codziennie chodzi do szkoły."), "On codziennie chodzi do szkoły."),
                Question("Jaki jest czas przeszły czasownika 'jeść'?", listOf("jadłem", "jem", "zjadłem", "jadł"), "jadłem"),
                Question("Wybierz poprawną formę zaimka: 'To jest ___ książka.'", listOf("mój", "moje", "moją", "moja"), "moja")
            )
        ),
        "Test2" to mapOf(
            "english" to listOf(
                Question("What is the plural of 'child'?", listOf("childs", "children", "childes", "childrens"), "children"),
                Question("Which sentence is correct?", listOf("He go to school.", "He goes to school.", "He going to school.", "He gone to school."), "He goes to school."),
                Question("Identify the adjective: 'She has a beautiful dress.'", listOf("She", "has", "beautiful", "dress"), "beautiful"),
                Question("What is the comparative form of 'good'?", listOf("gooder", "better", "best", "more good"), "better"),
                Question("Which sentence is in the past tense?", listOf("She is running.", "She runs.", "She ran.", "She running."), "She ran."),
                Question("Choose the correct preposition: 'She is sitting ___ the table.'", listOf("on", "in", "under", "at"), "on")
            ),
            "polish" to listOf(
                Question("Jaki jest czas przeszły czasownika 'jeść'?", listOf("jadłem", "jem", "zjadłem", "jadł"), "jadłem"),
                Question("Które zdanie jest poprawne?", listOf("On idę do szkoły.", "On idzie do szkoły.", "On idą do szkoły.", "On idziesz do szkoły."), "On idzie do szkoły."),
                Question("Wskaż przymiotnik: 'To jest duży dom.'", listOf("To", "jest", "duży", "dom"), "duży"),
                Question("Wybierz poprawne zdanie:", listOf("Ona ma więcej książek od mnie.", "Ona ma więcej książek niż ja.", "Ona ma książek więcej niż mnie.", "Ona więcej książek ma niż ja."), "Ona ma więcej książek niż ja."),
                Question("Wybierz poprawne zdanie.", listOf("Gdybym był bogaty, kupiłbym nowy samochód.", "Gdybym jest bogaty, kupiłbym nowy samochód.", "Gdybym byłem bogaty, kupiłby nowy samochód.", "Gdybym będzie bogaty, kupię nowy samochód."), "Gdybym był bogaty, kupiłbym nowy samochód."),
                Question("Wybierz poprawną formę czasownika: 'Ona ___ w parku.'", listOf("biegła", "biega", "biegł", "biegają"), "biega")
            )
        ),
        "Test3" to mapOf(
            "english" to listOf(
                Question("What is the plural of 'child'?", listOf("childs", "children", "childes", "childrens"), "children"),
                Question("Which sentence is correct?", listOf("He go to school.", "He goes to school.", "He going to school.", "He gone to school."), "He goes to school."),
                Question("Identify the adjective: 'She has a beautiful dress.'", listOf("She", "has", "beautiful", "dress"), "beautiful"),
                Question("Which is the correct form of the verb: 'He ___ to the store every day.'", listOf("goes", "going", "gone", "go"), "goes"),
                Question("Choose the correct sentence in future tense:", listOf("She will going to the store.", "She is going to the store.", "She will go to the store.", "She going to the store."), "She will go to the store."),
                Question("What is the comparative form of 'happy'?", listOf("happyer", "more happy", "happier", "happiest"), "happier")
            ),
            "polish" to listOf(
                Question("Uzupełnij zdanie:\n" +
                        "„Kiedy ________ (przyjechać) do pracy, zaczęła padać deszcz.”", listOf("przyjechałem", "przyjechał", "przyjechałam", "przyjechaliśmy"), "przyjechałam"),
                Question("Uzupełnij zdanie odpowiednią formą czasownika.\n" +
                        "„On ________ (czytać) książkę, gdy zadzwonił telefon.”", listOf("czyta", "czytać", "czytał", "przeczytał"), "czytał"),
                Question("Wybierz poprawną formę przymiotnika.\n" +
                        "„To jest ________ film, który widziałem w życiu.”", listOf("najgorszy", "najgorszy z", "najgorsze", "najgorsza"), "najgorszy"),
                Question("Uzupełnij zdanie odpowiednią formą czasownika w czasie przyszłym:\n" +
                        "„Jutro ________ (iść) do kina.”", listOf("będę idź", "pójdę", "poszłem", "iść"), "pójdę"),
                Question("Zaznacz poprawne zdanie w czasie przeszłym.", listOf("On nie rozmawiał z nim od tygodnia.", "On nie rozmawia z nim od tygodnia.", "On nie rozmawia z nim od tygodni.", "On nie rozmawia od tygodnia."), "On nie rozmawiał z nim od tygodnia."),
                Question("Wybierz odpowiednią formę przymiotnika: 'To jest ________ miasto.'", listOf("najpiękniejszy", "piękna", "piękniejszy", "piękny"), "piękny")
            )
        )
    )

    fun getQuestions(testId: String, language: String): List<Question> {
        return questionsMap[testId]?.get(language) ?: emptyList()
    }
}
