local total = tonumber(redis.call('GET', KEYS[1]))
local winning = tonumber(redis.call('GET', KEYS[2]))
local result = 1;

if (winning == 1)
    then  result = 0
elseif (total == 1)
    then result = 2
else
    result = 1
end


if (total > 1)
    then
    total = total -1
    if (winning == 0 and tonumber(ARGV[1]) ==1 ) then redis.call('SET', KEYS[2], 1)  end
else
    total = 100
    winning = 0
    redis.call('SET', KEYS[2], winning)
end


redis.call('SET', KEYS[1], total)


return result