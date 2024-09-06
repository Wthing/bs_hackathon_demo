new Vue({
    el: '#app',
    data: {
        currentPage: 'homepage',
        events: [], // Для карусели событий
        news: [], // Для бесконечной ленты новостей
        topFiveBooks: [],
        books: [], // Для поиска книг
        searchQuery: {
            title: '',
            isbn: '',
            author: ''
        }
    },
    created() {
        // Инициализация данных, например, загрузка событий и новостей с сервера
        this.loadEvents();
        this.loadNews();
        this.loadPopularBooks();
    },
    methods: {
        changePage(page) {
            this.currentPage = page;
        },
        loadEvents() {
            // Загрузка событий для карусели
            // Пример: fetch('/api/events').then(response => response.json()).then(data => this.events = data);
        },
        loadNews() {
            // Загрузка новостей для бесконечной ленты
        },
        async loadPopularBooks() {
            try {
                // Make a GET request to your server endpoint
                const response = await axios.get('https://localhost:8080/books/top_five'); // Replace with your server URL
                this.books = response.data.topFiveBooks; // Assuming the JSON response has a 'topBooks' key
            } catch (error) {
                console.error('Error fetching popular books:', error);
            }
        },
        searchBooks() {
            // Поиск книг по введенным данным
            // Пример: fetch(/api/books?title=${this.searchQuery.title}&isbn=${this.searchQuery.isbn}&author=${this.searchQuery.author}).then(response => response.json()).then(data => this.books = data);
        }
    },
    template: `
        <div>
            <header id="header">
                
                <div @click="changePage('homepage')" class="logo-placeholder"><img src="../img/logo.png" alt="logo"></div>
                <nav>
                    <ul>
                        <li @click="changePage('homepage')">Главная</li>
                        <li @click="changePage('about')">О библиотеке</li>
                        <li @click="changePage('books')">Книги</li>
                        <li @click="changePage('forums')">Форумы</li>
                        <li><a href="/register">Зарегистрироваться</a> / <a href="/login">Войти</a></li>
                    </ul>
                </nav>
            </header>

            <main>
                <section v-if="currentPage === 'homepage'">
                <h1>Карагандинская областная универсальная научная библиотека</h1>
                    <!-- Живая карусель событий -->
                    <div class="carousel">
                        <div v-for="event in events" class="carousel-item">
                            {{ event.name }}
                        </div>
                    </div>
                    <!-- Бесконечная лента новостей -->
                    <div class="news-feed">
                        <div v-for="newsItem in news" class="news-item">
                            {{ newsItem.title }}
                        </div>
                    </div>
                </section>

                <section  v-if="currentPage === 'about'">
                <div class="container">
                    <aside id="aside">
                        <a href="#address">Адрес</a>
                        <a href="#history">История</a>
                        <a href="#directors">Руководители</a>
                        <a href="#docs">Документы</a>
                    </aside>
                    <div class="content">
                    <article id="address">
                        <h2>Адрес</h2>
                        <p>Республика Казахстан, 100009, Карагандинская область,
                         город Караганда, район им. Казыбек би, ул. Ерубаева, д. 44.</p>
                    </article>
                    <article id="history">
                        <h2>История</h2>
                        <p>Библиотека является ровесницей города, она открылась в 1934г. с фондом 2 тыс. экз. в клубе угольщиков, в 1938 г. 
                        получила статус областной. Первым библиотекарем и организатором работы стала Полина Петровна Кривцова, окончившая в 
                        городе Тамбов курсы библиотекарей. Фонд библиотеки вначале пополнялся в основном поступлениями из Москвы. Это была х
                        удожественная, политическая и техническая литература. \n

Президиум Карагандинского облисполкома своим Постановлением от 10 сентября 1937г. выделил новое, более вместительное помещение в центре город
а и принял решение о капитальном ремонте библиотеки. С марта 1938 г. библиотека расположилась в здании по адресу Большая Михайловка, ул. Некра
сова (ныне Музейный переулок), д.4.\n

Деятельность библиотеки во время войны была направлена в первую очередь на обслуживание эвакогоспиталей: проводились конференции, громкие
 читки, лекции. В обязательном порядке библиотекари знакомили население со сводками Совинформбюро, решениями Государственного Комитета Обороны
  (ГКО).\n

С 5 сентября 1944 г. заведующей Карагандинской областной библиотекой стала Шишикина Мария Михайловна, работавшая в библиотеке с 10 сентября 
1941 г. Под ее руководством библиотека после окончания войны в 1947 г. переехала в просторное здание в центре города по адресу: ул. Ленина, 1.\n

К услугам читателей были богатые по тем временам книжные фонды и полные комплекты периодических изданий. Обслуживанием были заняты квалифициро
ванные библиотекари. Читальный зал библиотеки был постоянно полон.\n

В послевоенные годы библиотека обращается к популяризации творчества казахских писателей. Один за другим выходят произведения Г. Мусре
пова, С. Муканова, Г. Мустафина, А. Абишева, Х. Ергалиева и др. В библиотеке организовываются книжные выставки, проводятся обзоры, беседы, 
конференции.

</p>
                    </article>
                    <article id="directors">
                        <h2>Руководители</h2>
                        <p>Шаймуханбетова Айнур Сакеновна\n

Шаймуханбетова Айнур Сакеновна родилась 8 января 1985г. в г. Каркаралинск Карагандинской области. После окончания средней школы №4 в с. 
Коктас поступила в Карагандинский государственный университет им.Е.А.Букетова по специальности «культурология», затем в период с 2006г. по 
2008г. обучалась в магистратуре.\n

Трудовую деятельность начала  библиотекарем в Карагандинской ОУНБ им.Н.В.Гоголя. В 2008г. в рамках педагогической практики работала преподава
телем общественных дисциплин  в Казахстанском естественно-гуманитарном колледже. С 2010 г. на государственной службе: с 2010 по 2016г. - 
главный специалист управления культуры, архивов и документации Карагандинской области. С 2016г. перешла на работу в ОУНБ им.Н.В.Гоголя в 
должности ученого секретаря, затем в должности заместителя директора.\n

Заочно обучалась в Центрально-Казахстанской академии и получила  квалификацию бакалавра экономики и бизнеса.\n

В марте 2019 г. была назначена руководителем Карагандинской областной специальной библиотеки для незрячих и слабовидящих граждан.\n

Под ее руководством библиотека в 2020 году  стала  победителем в номинации «Лучший инклюзивный онлайн проект библиотек-2020»  на Фестивале о
рганизаций и работников сферы культуры и искусства «Рухани қазына - 2020» в онлайн формате, а в 2022 году в номинации «Лучшая библиотека для л
юдей с особыми потребностями» на V Фестивале организаций работников культуры и искусства «Рухани қазына – 2022» (г. Туркестан).\n

Подписаны  трехсторонние меморандумы с Санкт-Петербургской Государственной специальной Центральной библиотекой для незрячих и слабовидящих гр
аждан (РФ),  Свердловской областной специальной библиотекой для незрячих и слабовидящих граждан (РФ) и Павлодарской областной специальной би
блиотекой, для реализации проекта «Сарыарка - любимый край» подписан меморандум с Департаментом уголовно-исполнительной системы Карагандинск
ой области МВД РК. Благодаря ее организаторским и профессиональным качествам на высоком уровне был проведен 50-летний юбилей библиотеки, Межд
ународная научно-практическая конференция «Тенденции развития библиотек в формировании инклюзивного общества» (2022).\n

10 октября 2023г. была переведена на должность руководителя Карагандинской областной универсальной научной библиотеки им. Н.В.Гоголя.</p>
                    </article>
                    <article id="docs">
                        <h2>Документы</h2>
                        <p>...</p>
                    </article>
                    </div>
                    </div>
                </section>

                <section v-if="currentPage === 'books'">
                    <div class="form-group">
  <label for="book-title">Название книги:</label>
  <input id="book-title" v-model="searchQuery.title" type="text" placeholder="Война и мир">
</div>

<div class="form-group">
  <label for="book-isbn">ISBN (необязательно):</label>
  <input id="book-isbn" v-model="searchQuery.isbn" type="text" placeholder="978-3-16-148410-0">
</div>

<div class="form-group">
  <label for="book-author">Фамилия автора:</label>
  <input id="book-author" v-model="searchQuery.author" type="text" placeholder="Толстой">
</div>

                        <button type="submit">Найти</button>
                    </form><div class="book-grid">
                        <div v-for="book in books" class="book-item">
                            <h3>{{ book.title }}</h3>
                            <p>{{ book.author }}</p>
                        </div>
                    </div>

                     <h2>Популярные книги</h2>
                     <div class="book-grid">
                     <div v-for="book in books" :key="book.id" class="book-item">
                     <h3>{{ book.title }}</h3>
      <p>{{ book.author }}</p>
    </div>
  </div>
                </section>

                <section v-if="currentPage === 'forums'">
                    <p>Форумы будут здесь...</p>
                </section>
            </main>

            <footer>
                <p>Copyright © 1999–2024, Қарағанды ОӘҒК</p>
            </footer>
        </div>
    `
});