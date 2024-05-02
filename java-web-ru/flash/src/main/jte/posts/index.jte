@import exercise.dto.posts.PostsPage
@param PostsPage page

@template.layout.page(
    content = @`

        @if(page.getFlash() != null)
        <div class="alert-success">
            ${page.getFlash()}
        </div>
        @endif

    <table class="table-primary">
        @for(var post : page.getPosts())
            <tr>
                <td>
                    <a href="posts/${post.getId()}">${post.getName()}</a>
                </td>
            </tr>
        @endfor
    </table>
`
)
