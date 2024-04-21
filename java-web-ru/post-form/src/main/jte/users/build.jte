@template.layout.page(
    content = @`
<form action="/users" method="post">
    <div>
        <label>
            First name
            <input type="text" required name="firstName">
        </label>
    </div>
    <div>
        <label>
            Last name
            <input type="text" required name="lastName">
        </label>
    </div>
    <div>
        <label>
            Email
            <input type="text" required name="email">
        </label>
    </div>
    <div>
        <label>
            Password
            <input type="text" required name="password">
        </label>
    </div>
    <div>
        <label>
            <input type="submit">
        </label>
    </div>
</form>
`
)
