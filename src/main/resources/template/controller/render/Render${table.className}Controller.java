@Controller
public class RenderController {

    @BussinessLog(value = "进入${table.classNameFirstLower}管理页面")
    @RequiresPermissions("${table.classNameFirstLower}s")
    @GetMapping("/${table.classNameFirstLower}s")
    public ModelAndView ${table.classNameFirstLower}s(Model model) {
        return ResultUtil.view("${table.classNameFirstLower}s");
    }
}