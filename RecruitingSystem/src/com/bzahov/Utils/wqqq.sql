/*показва колко активни обяви имаме по категории
*/
select count(jobAds.id) from RecruitmentSystem.dbo.jobAds
join categories c on c.id = jobAds.categoryID
join RecruitmentSystem.dbo.records r on r.jobAdsID = jobAds.id
group by jobAds.id
select * from RecruitmentSystem.dbo.records


SELECT categories.Name as CategoryName, Count(JobAds.ID) as numberJobs
FROM categories
       INNER JOIN JobAds ON categories.Id = jobAds.CategoryId
       INNER JOIN Records ON jobAds.ID = Records.jobAdsID
WHERE JobAds.isActive = 1
GROUP BY Categories.Id,Categories.Name
order by sum(jobAdsID) desc, CategoryName asc, jobAds.name