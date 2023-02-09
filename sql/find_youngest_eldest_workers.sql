select  'YOUNGEST' as type, name, birthday from worker
group by name
having datediff(DAY, birthday , current_date) = (
select  min(datediff(DAY, birthday , current_date))
from worker)

union all

select  'ELDEST' as type, name, birthday from worker
group by name
having datediff(DAY, birthday , current_date) = (
select  max(datediff(DAY, birthday , current_date))
from worker)

