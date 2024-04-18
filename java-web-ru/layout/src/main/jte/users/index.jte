@import exercise.dto.users.UsersPage

@param UsersPage page

@template.layout.page(
content = @`
<h1>Пользователи</h1>
<div>
@for(var user : page.getUsers())

<p>
<a href="/users/${user.getId()}">${user.getFirstName()} ${user.getLastName()}</a>
</p>

@endfor
</div>`,
footer = @`
    <div>Its cruppy footer<div>
`
)