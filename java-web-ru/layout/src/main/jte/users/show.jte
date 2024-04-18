@import exercise.dto.users.UserPage

@param UserPage page

@template.layout.page(
content = @`
        <div>${page.getUser().getFirstName()} ${page.getUser().getLastName()}</div>
        <div>${page.getUser().getEmail()}   </div>
`,
footer = @`
        <div>Its cruppy footer<div>
`
)