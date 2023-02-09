select 
    concat('Project ', project.id) as Name,
    sum(salary * datediff(MONTH, start_date, finish_date)) as Price 
from project_worker
join worker on worker.id = project_worker.worker_id
join project on project.id = project_worker.project_id
group by project_id