@import exercise.dto.posts.PostPage
@param PostPage page

@template.layout.page(
    content = @`

    <div class="mx-auto p-4 py-md-1">
        <a href="/posts">Все посты</a>
    </div>

    <div class="mx-auto p-4 py-md-1">
        <h3>${page.getPost().getName()}</h3>

    </div>
    <div class="mx-auto p-4 py-md-1">
        <p> ${page.getPost().getBody()} </p>

    </div>
`
)
