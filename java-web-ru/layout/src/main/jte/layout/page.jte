@import gg.jte.Content

@param Content content
@param Content footer

<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
          crossorigin="anonymous">

    <title>crap</title>
</head>
<body>
<div class="header">
    <a href="/">Main </a><a href="/users"> Users</a>
</div>
${content}
@if(!footer.isEmptyContent())
    ${footer}
@endif
</body>
</html>