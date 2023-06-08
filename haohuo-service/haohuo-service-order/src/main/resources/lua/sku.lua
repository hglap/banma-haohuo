-- 获取目标sku的key
local sku = KEYS[1]
-- 当前扣减数量
local num = tonumber(ARGV[1])

-- 获取目标key的当前库存数量
local stock = tonumber(redis.call('GET', sku))
-- 返回结果
local result = 0
if stock >= num then
    -- 库存减去扣减数量
    redis.call('DECRBY', sku, num)
    result = 1
end

return result