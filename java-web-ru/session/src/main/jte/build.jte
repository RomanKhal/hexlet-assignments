@import exercise.util.NamedRoutes
@import exercise.dto.LoginPage
@param LoginPage page

@template.layout.page(

    content = @`
    <div class="mx-auto p-4 py-md-5">
        <div class="mx-auto p-4 py-md-5"> @if(page != null)
                ${page.getError()}
            @endif</div>
<form class="form" action="${NamedRoutes.loginPath()}" method="post">
    <div class="mb-3">
        <label class="form-label">
            Name
            <input type="text" class="form-control" name="name"/>
        </label>
    </div>
    <div class="mb-3">
        <label class="form-label">
            Password
            <input type="text" class="form-control" name="password" />
        </label>
    </div>
    <input type="submit" class="btn btn-primary" value="Войти" />
</form>
    </div>
`
)