SELECT films.name as filmName,
       c.name as categoryName,
       watchCount,
       earnings,
       info
from films
    join filmsCategory f on films.id = f.filmID
    join categories c on f.categoryID = c.id
where watchCount>0
group by watchCount,c.name, earnings, films.name, info
order by c.name asc , watchCount desc ,films.name asc
