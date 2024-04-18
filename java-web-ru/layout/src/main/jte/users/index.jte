@import exercise.dto.users.UsersPage

@param UsersPage page

@template.layout.page(
content = @`

    @for(var user : page.getUsers())
        <p><a href="/users/{${user.getId()}}">${user.getFirstName()} ${user.getLastName()}</a></p>
    @endfor
`,
footer = @`
    <div>Its cruppy footer<div>
`
)