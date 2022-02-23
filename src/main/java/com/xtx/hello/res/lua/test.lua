--无参函数
function hello()
    print 'hello'
end
--带参函数
function test(str)
    print(str["a"])
    return 'haha'
end

function main(pos)
    print(pos["x"])
    local LuaFunction = luajava.bindClass("com.xtx.hello.LuaFunction")

    local tmp=LuaFunction:GetBlock(pos["x"],pos["y"],pos["z"])

    return 'haha'
end