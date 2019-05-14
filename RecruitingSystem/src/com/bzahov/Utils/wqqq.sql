/*показва колко активни обяви имаме по категории
*/
select c.name,count(c.id) as  'JobAds total count'
from jobAds
join categories c on c.id = jobAds.CategoryID
where jobAds.isActive = 1
group by  c.id, c.name
order by count(c.id) desc,c.name desc

/*колко хора са кандидствали по всяка професия (QA, Developer, Manager, DevOps*/
select count(r.id) as  'People total count', j.name as jobName
from records r
            join jobAds j on j.id = r.jobAdsID
    /*where isActive = 1*/
group by j.id, j.name
order by count(r.id) desc


/*union
select c.name,count(c.id) as  'JobAds total count'
from jobAds
            join categories c on c.id = jobAds.CategoryID
where jobAds.isActive = 1
group by  c.id, c.name, jobads.name
/*order by count(c.id) desc,c.name desc, c.id desc*/
union
select j.name as jobName, count(r2.id) as  'People total count'
from records r2
            join jobAds j on j.id = r2.jobAdsID
    /*where isActive = 1*/
group by j.id, j.name*/



select count(r.id) as  'People total count', r.name, jobAds.name as jobName
from RecruitmentSystem.dbo.jobAds
join records r on r.jobAdsID = jobAds.id
/*where isActive = 1*/
group by jobads.name, r.id, r.name
order by r.name asc , count(r.id) desc

select count(r.id) as  'People total count', j.name as jobName
from records r
            join jobAds j on j.id = r.jobAdsID
    /*where isActive = 1*/
group by j.id, j.name
order by count(r.id) desc



/**/
SELECT categories.Name as CategoriesName, Count(JobAds.ID) as numberJobs
FROM categories
       INNER JOIN JobAds ON categories.Id = jobAds.CategoriesId
       INNER JOIN Records ON jobAds.ID = Records.jobAdsID
WHERE JobAds.isActive = 1
GROUP BY Categories.Id,Categories.Name
order by sum(jobAdsID) desc, CategoriesName asc, jobAds.name

select c.name,count(jobAds.id) as  'JobAds total count'
from RecruitmentSystem.dbo.jobAds
            join categories c on c.id = jobAds.CategoriesID
            join RecruitmentSystem.dbo.records r on r.jobAdsID = jobAds.id
where isActive = 1
group by c.name, jobads.name,jobAds.id, c.id
order by count(jobAds.id) desc,c.name desc

SELECT Categories.Name as Categories,
       Count(j.ID) as ActiveJob
       /*Count(records.Name) as ApplyingPeople*/
FROM Categories
            INNER JOIN JobAds j ON Categories.Id = j.categoryID
            /*INNER JOIN Records ON j.ID = Records.JobAdsId*/
            /*INNER JOIN Employee ON Records.EmployeeId = Employee.id*/
WHERE j.IsActive = 1
GROUP BY Categories.Id ,Categories.Name
order by Count(j.ID) asc/*, records.name

SELECT Categories.Name as Categories,
       Count(j.ID) as ActiveJob
       /*Count(records.Name) as ApplyingPeople*/
FROM Categories
            INNER JOIN JobAds j ON Categories.Id = j.categoryID
            /*INNER JOIN Records ON j.ID = Records.JobAdsId*/
            /*INNER JOIN Employee ON Records.EmployeeId = Employee.id*/
WHERE j.IsActive = 1
GROUP BY Categories.Id,Categories.Name
Group by Count(j.ID) asc/*, records.name