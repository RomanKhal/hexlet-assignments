@template.layout.page(
content = @`
    <a href="/users">Пользователи</a>
    <form action="/users" method="get">
        <input type="search" required name="term">
        <input type="submit" value="Search">
    </form>
`
)
