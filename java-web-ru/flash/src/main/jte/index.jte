@import exercise.util.NamedRoutes
@template.layout.page(
    content = @`
        <h2>Welcome to Hexlet</h2>
        <a href="${NamedRoutes.buildPostPath()}"> Create a post</a>
    `
)
