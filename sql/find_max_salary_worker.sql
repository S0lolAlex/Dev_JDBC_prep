select name,salary
from worker
group by name
having salary = (
select max(salary) 
from worker)