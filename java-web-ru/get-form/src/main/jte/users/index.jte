@import exercise.dto.users.UsersPage
@param UsersPage page
@template.layout.page(
content = @`
    <a href="/users">Пользователи</a>
    <form action="/users" method="get">
        <input type="search" required name="term">
        <input type="submit" value="Search">
    </form>
<table class="table table-dark table-striped">
    <thead>
    <tr>
        <th>
           Id
        </th>
        <th>Name</th>
    </tr>
    </thead>
    <tbody>
    @for(var user : page.getUsers())
    <tr><td>${user.getId()}</td><td>${user.getFirstName()} ${user.getLastName()}</td></tr>
    @endfor

    </tbody>
    </table>
`
)