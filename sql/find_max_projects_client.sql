select name, count(project_id) as project_count
from worker 
join project_worker on project_worker.worker_id = worker.id
where id in 
(
   select worker_id
   from  project_worker
   group by worker_id
   having  count(project_id) in (
       select count(project_id)
        from project_worker
        group by worker_id
        order by count(project_id) desc
         limit 1
	)
)
