package cn.com.mfish.sys.controller;

import cn.com.mfish.common.log.annotation.Log;
import cn.com.mfish.common.core.enums.OperateType;
import cn.com.mfish.common.core.web.Result;
import cn.com.mfish.sys.entity.DbConnect;
import cn.com.mfish.sys.req.ReqDbConnect;
import cn.com.mfish.sys.service.DbConnectService;
import cn.com.mfish.common.core.web.PageResult;
import cn.com.mfish.common.web.page.ReqPage;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.Arrays;

/**
 * @description: 数据库连接
 * @author: mfish
 * @date: 2023-03-13
 * @version: V1.0.0
 */
@Slf4j
@Api(tags = "数据库连接")
@RestController
@RequestMapping("/dbConnect")
public class DbConnectController {
	@Resource
	private DbConnectService dbConnectService;

	/**
	 * 分页列表查询
	 *
	 * @param reqDbConnect 数据库连接请求参数
	 * @return 返回数据库连接-分页列表
	 */
	@ApiOperation(value = "数据库连接-分页列表查询", notes = "数据库连接-分页列表查询")
	@GetMapping
	public Result<PageResult<DbConnect>> queryPageList(ReqDbConnect reqDbConnect, ReqPage reqPage) {
        PageHelper.startPage(reqPage.getPageNum(), reqPage.getPageSize());
	    return Result.ok(new PageResult<>(dbConnectService.list()), "数据库连接-查询成功!");
	}

	/**
	 * 添加
	 *
	 * @param dbConnect 数据库连接对象
	 * @return 返回数据库连接-添加结果
	 */
	@Log(title = "数据库连接-添加", operateType = OperateType.INSERT)
	@ApiOperation("数据库连接-添加")
	@PostMapping
	public Result<DbConnect> add(@RequestBody DbConnect dbConnect) {
		if (dbConnectService.save(dbConnect)) {
			return Result.ok(dbConnect, "数据库连接-添加成功!");
		}
        return Result.fail(dbConnect, "错误:数据库连接-添加失败!");
	}

	/**
	 * 编辑
	 *
	 * @param dbConnect 数据库连接对象
	 * @return 返回数据库连接-编辑结果
	 */
	@Log(title = "数据库连接-编辑", operateType = OperateType.UPDATE)
	@ApiOperation("数据库连接-编辑")
	@PutMapping
	public Result<DbConnect> edit(@RequestBody DbConnect dbConnect) {
		if (dbConnectService.updateById(dbConnect)) {
		    return Result.ok(dbConnect, "数据库连接-编辑成功!");
		}
		return Result.fail(dbConnect, "错误:数据库连接-编辑失败!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id 唯一ID
	 * @return 返回数据库连接-删除结果
	 */
	@Log(title = "数据库连接-通过id删除", operateType = OperateType.DELETE)
	@ApiOperation("数据库连接-通过id删除")
	@DeleteMapping("/{id}")
	public Result<Boolean> delete(@ApiParam(name = "id", value = "唯一性ID") @PathVariable String id) {
		if (dbConnectService.removeById(id)) {
			return Result.ok(true, "数据库连接-删除成功!");
		}
		return Result.fail(false, "错误:数据库连接-删除失败!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids 批量ID
	 * @return 返回数据库连接-删除结果
	 */
	@Log(title = "数据库连接-批量删除", operateType = OperateType.DELETE)
	@ApiOperation("数据库连接-批量删除")
	@DeleteMapping("/batch")
	public Result<Boolean> deleteBatch(@RequestParam(name = "ids") String ids) {
		if (this.dbConnectService.removeByIds(Arrays.asList(ids.split(",")))) {
		    return Result.ok(true, "数据库连接-批量删除成功!");
		}
		return Result.fail(false, "错误:数据库连接-批量删除失败!");
	}

	/**
	 * 通过id查询
	 *
	 * @param id 唯一ID
	 * @return 返回数据库连接对象
	 */
	@ApiOperation("数据库连接-通过id查询")
	@GetMapping("/{id}")
	public Result<DbConnect> queryById(@ApiParam(name = "id", value = "唯一性ID") @PathVariable String id) {
		DbConnect dbConnect = dbConnectService.getById(id);
		return Result.ok(dbConnect, "数据库连接-查询成功!");
	}
}
