@import exercise.util.NamedRoutes
@import exercise.dto.MainPage
@param MainPage page

@template.layout.page(
content = @`
    <div class="mx-auto">
    @if(page.getName() != null)
        current session is ${page.getName().toString()}
        <form action="${NamedRoutes.logoutPath()}" method="post">
            <input type="submit" class="btn btn-primary" value="Выйти" />
        </form>
    @else <form action="${NamedRoutes.buildSessionPath()}" method="get">
        <input type="submit" class="btn btn-primary" value="Войти" />
    </form> @endif
    </div>
`
)