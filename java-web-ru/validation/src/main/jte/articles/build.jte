@import exercise.dto.articles.BuildArticlePage
@param BuildArticlePage page

@template.layout.page(

content = @`
    @if(page.getErrors() != null)
        <ul>
            @for(var validator : page.getErrors().values())
                @for(var error : validator)
                    <li>${error.getMessage()}</li>
                @endfor
            @endfor
        </ul>
    @endif


    <form action="/articles" method="post" >
        <div>
            <label> title
                <input type="text" name="title" value="${page.getArticle() != null ? page.getArticle().getTitle() : ""}">
            </label>
        </div>
        <label for="1"> content
            <textarea id="1" name="content">${page.getArticle() != null ? page.getArticle().getContent() : ""} </textarea>
        </label>
        <div><input type="submit"></div>
    </form>

`
)