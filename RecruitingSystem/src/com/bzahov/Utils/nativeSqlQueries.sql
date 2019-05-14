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

group by j.id, j.name
order by count(r.id) desc


/* testtt


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
select c.name,count(jobAds.id) as  'JobAds total count'
from RecruitmentSystem.dbo.jobAds
            join categories c on c.id = jobAds.CategoryID
            join RecruitmentSystem.dbo.records r on r.jobAdsID = jobAds.id
where isActive = 1
group by c.name, jobads.name,jobAds.id, c.id
order by count(jobAds.id) desc,c.name desc
*/