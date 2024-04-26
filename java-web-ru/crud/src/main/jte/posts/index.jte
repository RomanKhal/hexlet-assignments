@import exercise.util.NamedRoutes;
@import exercise.dto.posts.PostsPage
@param PostsPage page

@template.layout.page(
content = @`
    <div class="mx-5 p-4 py-md-6 ">
        <main>
            <a href="${NamedRoutes.postsPath()}">Все посты</a>
            <div class="justify-content-center">
            <table class="table table-light justify-content-center">
                <thead>
                <tr>
                    <td>Name</td>
                </tr>
                </thead>
                <tbody>
                @for(var post : page.getPosts())
                    <tr>
                        <td ><a class="link-secondary " href="/posts/${post.getId()}">${post.getName()}</a></td>
                    </tr>
                @endfor
                </tbody>
            </table>
            </div>
            <nav aria-label="Page navigation example" >
                <ul class="pagination justify-content-center">
                    <li class="page-item">
                        <a class="page-link" href="posts?page=@if(page.getCurrentPage() >1)${page.getCurrentPage() - 1}@endif" aria-label="Previous">
<%--                            <span aria-hidden="true">&laquo;</span>--%>
                            Назад
                        </a>
                    </li>
                    <li class="page-item">
                        <a class="page-link" href="posts?page=${page.getCurrentPage() + 1}" aria-label="Next">Вперед
<%--                            <span aria-hidden="true">&raquo;</span>--%>
                        </a>
                    </li>
                </ul>
            </nav>
<%--            <nav aria-label="Page navigation example">--%>
<%--                <ul class="pagination">--%>
<%--                    <li class="page-item"><a class="page-link" href="">Назад</a></li>--%>
<%--                    <li class="page-item"><a class="page-link" href="posts?page=${page.getCurrentPage() + 1}">Вперед</a></li>--%>
<%--                </ul>--%>
<%--            </nav>--%>
        </main>
    </div>
`
)
